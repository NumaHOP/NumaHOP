set shell := ["bash", "-c"]

default: info
	@just --list

# Get the MVN_ARGS so that maven respect it inside this file.
export MAVEN_ARGS := env('MAVEN_ARGS', '')

# Print information
info:
	@echo -e "Binaries:"
	@echo -e "\tfound mvn binary: $(which mvn)"
	@echo -e "\tfound npm binary: $(which npm)"
	@echo -e "\tfound docker binary: $(which docker)"
	@echo ""
	@echo -e "Environement:"
	@echo -e "\tMaven extra args: $MVN_ARGS"
	@echo ""

alias f := format
# Formats the front end and back-end code
#[arg('target', pattern='all|front|back')]
format target='all':
	@case '{{target}}' in \
		'all') npm run format; mvn sortpom:sort spring-javaformat:apply;; \
		'front') npm run format;; \
		'back') mvn sortpom:sort spring-javaformat:apply;; \
	esac \

alias cl := clean
# Cleans the repository
# [arg('target', pattern='all|front|back')]
clean target='all':
	@case '{{target}}' in \
		'all') npm cache clean --force ;mvn clean ;; \
		'front') npm cache clean --force ;; \
		'back') mvn clean ;; \
	esac \

alias c := check
# Runs the checks
# [arg('target', pattern='all|front|back')]
check target='all':
	@case '{{target}}' in \
		'all') npm run check; npm run lint;; \
		'front') npm run check; npm run lint;; \
		'back') mvn validate;; \
	esac  \

alias t := test
# Launch tests
# [arg('target', pattern='all|front|back')]
test target='all' testpat='*':
	@case '{{target}}' in \
		'all') npm run test; mvn test -Dfast -Dtest="{{testpat}}";; \
		'front') npm run test;; \
		'back') mvn test -Dfast -Dtest="{{testpat}}";; \
	esac \

alias doc := document
# Build the documentation
# [arg('target', pattern='all|front|back')]
document target='all':
	@case '{{target}}' in \
		'all') npm run doc; mvn javadoc:javadoc -Dskip-front-end -Dfast;; \
		'front') npm run doc;; \
		'back') mvn javadoc:javadoc -Dskip-front-end -Dfast;; \
	esac \

alias b := build
# Launch the build
# [arg('target', pattern='all|front|back|docker')]
build target='all':
	@case '{{target}}' in \
		'all') mvn package -Dfast -Pdocker,webapp;; \
		'front') npx grunt build --force;; \
		'back') mvn package -Dfast=true -Dskip-front-end=true -DskipTests;; \
		'docker') mvn package -Dfast -Pdocker,webapp;; \
	esac\

alias d := docker
# Short hands to manage the docker composition
# [arg('image', pattern='all|env|nh|db|es|mail|sftp', help="env corresponds to db + es + mail + sftp and all to env + nh")]
# [arg('action', pattern='ps|top|down|up|clean|stop|logs|shell|reset')]
docker action='ps' image='all' extra='':
	@case '{{action}}' in \
		'setup') docker build -t numahop-run src/main/docker --target run ;; \
		'ps') docker compose -p numahop ps ;; \
		'top') docker compose -p numahop top ;; \
		'down') docker compose -p numahop -f src/main/docker/docker-compose.{{image}}.yml rm -f -s;; \
		'up') docker compose -p numahop -f src/main/docker/docker-compose.{{image}}.yml up -d;; \
		'clean') docker compose -p numahop -f src/main/docker/docker-compose.{{image}}.yml rm -f -s -v;; \
		'stop') docker compose -p numahop -f src/main/docker/docker-compose.{{image}}.yml stop;; \
		'logs') docker compose -p numahop -f src/main/docker/docker-compose.{{image}}.yml logs -f;; \
		'shell') [[ "i-nh i-db i-es i-mail i-sftp" == *"i-{{image}}"* ]] && \
			docker compose -p numahop -f src/main/docker/docker-compose.{{image}}.yml exec numahop-{{image}} bash;; \
		'reset') [[ "i-nh i-db i-es i-sftp" == *"i-{{image}}"* ]] && \
			docker compose -p numahop -f src/main/docker/docker-compose.{{image}}.yml rm -f -s -v; \
			docker volume rm numahop_numahop-{{image}}-volume;; \
		'exec') [[ "i-nh i-db i-es i-mail i-sftp" == *"i-{{image}}"* ]] && \
			docker compose -p numahop -f src/main/docker/docker-compose.{{image}}.yml exec numahop-{{image}} {{extra}};; \
	esac\
