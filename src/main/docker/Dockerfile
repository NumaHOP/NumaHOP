# base image
FROM eclipse-temurin:17-jre AS base

ENV DOCKER_USER_ID=185
ENV DOCKER_GROUP_ID=$DOCKER_USER_ID

ENV DOCKER_USER_NAME=numahop
ENV DOCKER_GROUP_NAME=$DOCKER_USER_NAME
ENV DOCKER_USER_HOME=/home/$DOCKER_USER_NAME

RUN groupadd -r "$DOCKER_GROUP_NAME" -g "$DOCKER_GROUP_ID" \
 && useradd -r -u "$DOCKER_USER_ID" -g "$DOCKER_GROUP_ID" -m -d "$DOCKER_USER_HOME" -s /sbin/nologin "$DOCKER_USER_NAME" \
 && chmod 0770 "$DOCKER_USER_HOME"

RUN apt-get update \
 && apt-get install -y tesseract-ocr imagemagick libimage-exiftool-perl libyaz5 \
 && apt-get clean

ENV IMAGEMAGICK_CONVERT_PATH=/usr/bin/convert
ENV IMAGEMAGICK_IDENTIFY_PATH=/usr/bin/identify
ENV EXIFTOOL_PATH=/usr/bin/exiftool
ENV TESSERACT_PATH=/usr/bin/tesseract

# run image
FROM base AS run

RUN mkdir -p /opt/pgcn \
 && chown ${DOCKER_USER_ID}:${DOCKER_GROUP_ID} /opt/pgcn

USER ${DOCKER_USER_ID}:${DOCKER_GROUP_ID}

# build image: compiles the front end and the back end, only needs docker on the host.
# The build context is the root of the repository (see the .dockerignore there).
FROM eclipse-temurin:17-jdk AS build

WORKDIR /src

COPY . .

# Maven, npm and node downloads are kept in BuildKit caches between builds.
RUN --mount=type=cache,target=/root/.m2 \
    --mount=type=cache,target=/src/.npm \
    ./mvnw -B package -Dfast -DskipTests \
 && cp target/numahop-*.jar /numahop.jar

# app image: the build image output on top of the run image, same settings as the jib image.
FROM run AS app

COPY --from=build --chown=${DOCKER_USER_ID}:${DOCKER_GROUP_ID} /numahop.jar /app/numahop.jar

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS

EXPOSE 8080

ENTRYPOINT ["java", "-XX:+HeapDumpOnOutOfMemoryError", "-XX:+ExitOnOutOfMemoryError", "-XX:MaxRAMPercentage=60.0", "-jar", "/app/numahop.jar"]
