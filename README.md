# posts-api

The backend API for interacting with posts on optimistic.ninja.

## Features

* WebFlux
* TLSv1.2 and 1.3 using strong cipher suites
* OpenAPI generator for service
  * Spec-first controller interface/model generation 
  * Lombok
  * Model validations
  * OAS3 annotations
* SpringDoc
* RBAC via Auth0
* R2DBC with PostgreSQL for a fully reactive API
* Database migrations via Flyway
* Spotless code formatting
  * Gradle
  * Groovy
  * Java
  * SQL
* Segmented tests for unit/smoke
  * TestContainers
  * Spock

## Requirements

* Java 17
* Docker
* docker-compose
* [Configured Auth0 tenant](auth0/README.md)

## Environment Variables

| Variable | Description
|---|---|
|SPRING_PROFILES_ACTIVE| override active Spring profile(s)
|LOCAL_KEYSTORE_PASSWORD| keystore password

## Profiles

| Environment | Description
|---|---|
|local| local development
|prod| production (TODO)

## Roles/Permissions

| Role | Description | Permissions
|---|---|---|
|user| Standard user|
|admin| Administrator| posts:g:w
|creator| User who can create and modify their own posts| posts:l:w