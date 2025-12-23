# Current Session Context & Technical Decisions

## Architecture Standards
* **Stack:** Java 21, Spring Boot 3.3+, Vaadin Flow 24 (Pure Java UI), PostgreSQL.
* **Pattern:** Service-Oriented Monolith.
* **UI Strategy:** Master-Detail views using Vaadin `SplitLayout`. No client-side React/TS.
* **Database:** JPA Entities are the source of truth. `ddl-auto=update` is active for dev.
* **IDs:** All Primary Keys must be `UUID`.

## Current Progress
* **Phase:** Completed Phase 2 (Ecosystem).
* **Entities:** `Organization`, `Department`, `Team` are implemented and working.
* **Views:** `OrganizationProfileView` and `EcosystemView` are functional.

## Immediate Next Steps (Session 4)
* **Goal:** "The Genome: Human & Role".
* **Task:** Implement `Employee` entity and authentication.
* **Reference:** We are porting logic from the legacy Node.js repo (specifically `replitAuth.ts` concepts and `User` schema).