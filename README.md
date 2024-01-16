# SpringBoot Auth0 PKCE Integration

Welcome to this project! It's a straightforward implementation of Auth0 with Spring Boot, focusing on the PKCE (Proof Key for Code Exchange) method. Designed for both learning and practical use, this repository demonstrates how to enhance security in public clients using PKCE in a Java backend environment. Dive in to see how Spring Boot and Auth0 can work together to create a more secure authentication process!
## Features

- Spring Boot and Spring Security setup.
- Integration with Auth0 for authentication.
- Utilization of the PKCE flow for enhanced security.
- Exception handling and global error responses.
- User entity and repository setup for data persistence.
- Example endpoints for user registration and data retrieval.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java JDK 11 or later.
- Maven for dependency management.
- An Auth0 account and an Auth0 application set up for PKCE flow.

## Setting Up

To set up the project, follow these steps:

1. Clone the repository:

   ```
   git clone https://github.com/FadiBadarni/SpringBoot-Auth0-PKCE.git
   ```
2. Navigate to the project directory:
   ```
   cd SpringBoot-Auth0-PKCE
   ```
3. Install the dependencies:
   ```
    mvn install
   ```
4. **Environment Configuration**

    Set up the following environment variables or add them to your `application.yml` for application configuration:

    - `AUTH0_USER_INFO_URL`: Specify the URL for Auth0 User Info. This is used for fetching user details after authentication.
    - `ALLOWED_ORIGINS`: Define the origins allowed for Cross-Origin Resource Sharing (CORS). This is essential for ensuring your API can be accessed from specified client applications.

    ### Example Configuration in application.yml

    Add these lines to your `application.yml` under the appropriate sections:

    ```yaml
    app:
      cors:
        allowed_origins: ${ALLOWED_ORIGINS}  # Add your allowed origins here

      auth0:
        user_info_url: ${AUTH0_USER_INFO_URL}  # Auth0 User Info URL

    # Other configurations
    spring:
      security:
        oauth2:
          resourceserver:
            jwt:
              issuer-uri: https://${AUTH0_DOMAIN}/
              jwk-set-uri: https://${AUTH0_DOMAIN}/.well-known/jwks.json
    ```

    Replace `${ALLOWED_ORIGINS}` and `${AUTH0_USER_INFO_URL}` with the actual values or ensure these environment variables are set in your environment.

    **Note:** The `${AUTH0_DOMAIN}` placeholder in the JWT configuration should also be replaced with your Auth0 domain.
