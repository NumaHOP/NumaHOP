# Changelog

All changes to this project should be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to calendar versioning as of 26.03 with bianual releases (yy.03 and yy.09).
## [Unreleased] 27.03

### Added
### Changed 
### Removed
### Fixed
### Breaking changes

## [26.09]

### Added

- Added a new habilitation for the admin tools to be used by any user having the habilitation on his profile.
- Added an `exec` sub rule for docker in the `justfile`.

### Changed 

- Renamed the java package id from `fr.progilone` to `org.numahop` rename the artifact id from `pgcn` to `numahop`.
- Changed the way the yaz4j is bundled for java. Now only the `libyaz5` package is needed on the server for Z39.50 to work.
- Updated the SpringBoot dependency from 3.0.2 to 3.5.16.

### Removed

### Fixed

- The default configuration for numahop exposed unsafe actuators endpoints by default. Those were disabled in the default config.
- Minor fixes in the `justfile`

### Breaking changes

## [26.03]

Here are the latest chages since last release. Once a new realease is made just move these chages in a section with the version number.

### Added

- `justfile` replacing the `Makefile`.
- `CONTRIBUTING.md` markdown file explaining the contributing guide
- Configuration option to set the ElasticSearch index name separator separating the prefix from the index name.
- Configuration option to set the Internet Archive url to use for internet archive repository.
- Ability for NumaHOP to output logs in json. (Disabled by default for backward compatibility)
- Ability for NumaHOP to generate front-end and back-end code documentation.
- `spring-openapi-starter-webmvc-ui` dependency for generating the openapi spec. (not functional because api is not compilent with the spec). 

### Changed 

- The build configuration has radicaly changed.
- Changed formatters to spring-javaformat.
- Updated jsch dependency.
- Reformat `@Query` annotations due to formatter change.
- Updated `jib-maven-plugin` build dependency.
- Fix a bug where a distant xml schema definition is inaccessible from the server. 

### Removed

- `Makefile`
- Ability to download logs from the instance.
- Unnecessary, Unused, Old files in the root of the directory. 

### Fixed

- Make the details button on the docunit preview be able to be opened in a new tab.
- Rework the Mail parsing for the CINES. 

### Breaking changes
Build setup changed quite a lot: `mvn package` is now sufisant to get a complete jar for NumaHOP. Optionaly you can add `-Dfast` to skip checks as these should always pass on the master branch.

The CINES mail parsing functionality was reworked to follow changes due to the migration to VITAM the CINES did.


## [2.3.1] - 2025-01-31

Last version released by TECH'Advantage.

[Unreleased]: https://github.com/numahop/numahop/compare/26.09..HEAD
[26.09]: https://github.com/numahop/numahop/tree/26.09
[26.03]: https://github.com/numahop/numahop/tree/26.03
[2.3.1]: https://github.com/numahop/numahop/tree/2.3.1
