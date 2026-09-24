# base image
FROM eclipse-temurin:17-jre AS base

ENV DOCKER_USER_ID 185
ENV DOCKER_GROUP_ID $DOCKER_USER_ID

ENV DOCKER_USER_NAME numahop
ENV DOCKER_GROUP_NAME $DOCKER_USER_NAME
ENV DOCKER_USER_HOME /home/$DOCKER_USER_NAME

RUN groupadd -r "$DOCKER_GROUP_NAME" -g "$DOCKER_GROUP_ID" \
 && useradd -r -u "$DOCKER_USER_ID" -g "$DOCKER_GROUP_ID" -m -d "$DOCKER_USER_HOME" -s /sbin/nologin "$DOCKER_USER_NAME" \
 && chmod 0770 "$DOCKER_USER_HOME"

RUN apt-get update \
 && apt-get install -y tesseract-ocr imagemagick libimage-exiftool-perl libyaz5 \
 && apt-get clean

ENV IMAGEMAGICK_CONVERT_PATH /usr/bin/convert
ENV IMAGEMAGICK_IDENTIFY_PATH /usr/bin/identify
ENV EXIFTOOL_PATH /usr/bin/exiftool
ENV TESSERACT_PATH /usr/bin/tesseract

# run image
FROM base AS run

RUN mkdir -p /opt/pgcn \
 && chown ${DOCKER_USER_ID}:${DOCKER_GROUP_ID} /opt/pgcn

USER ${DOCKER_USER_ID}:${DOCKER_GROUP_ID}

# build image
FROM base AS build

RUN apt-get update \
 && apt-get install -y openjdk-17-jdk-headless maven bzip2 \
 && apt-get install -y ruby-dev ruby-ffi \
 && apt-get clean \
 && gem install compass

ENV JAVA_HOME /usr/lib/jvm/java-17-openjdk-amd64

USER ${DOCKER_USER_ID}:${DOCKER_GROUP_ID}
