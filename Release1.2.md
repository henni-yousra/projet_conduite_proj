# Release 1.2

## **Release Date**
[Insert release date]

## **Overview**
Release 1.2 focuses on enhancing user functionality, improving API performance, and resolving issues identified in previous sprints.

## **New Features**
- **User Authentication Enhancements**
  - Improved login process with multi-factor authentication.
  - Password recovery via email.

- **Data Management**
  - CRUD operations for [specific entities].
  - Pagination and sorting added to tables.

- **Improved UI**
  - Redesigned dashboard with responsive layouts.
  - Better error messages for failed actions.

## **Bug Fixes**
- Resolved database timeout issues on bulk queries.
- Fixed session expiry errors on frontend.

## **Technical Enhancements**
- Upgraded to Angular 16 for better performance.
- Optimized database queries for faster response times.
- Improved Spring Boot API exception handling.

## **Known Issues**
- Minor UI glitches on smaller screen sizes.
- Some edge cases for password validation are still under review.

## **Instructions**
1. Pull the latest code from the `release-1.2` branch.
2. Run database migrations: `flyway migrate` or similar.
3. Start the backend and frontend applications.
