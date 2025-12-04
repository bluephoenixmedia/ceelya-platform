# Ceelya BCID Platform: Master Architecture & Roadmap

**Version:** 1.0  
**Date:** December 4, 2025  
**Architect:** Gemini (Senior Software Architect)

## 1. Executive Summary
The Ceelya BCID (Biomimetic Collective Intelligence Design) Platform is being re-engineered from a prototype Node/React stack to a scalable, enterprise-grade **Java 21** architecture. This transition prioritizes type safety, maintainability, and deep integration with the **Spring Ecosystem** for the future "Agentic Mesh."

## 2. Technology Stack (The "Hive" Foundation)

| Layer | Technology | Rationale |
| :--- | :--- | :--- |
| **Language** | **Java 21 (LTS)** | Modern, strictly typed, high performance. |
| **Framework** | **Spring Boot 3.3+** | Industry standard for dependency injection & microservices. |
| **Frontend** | **Vaadin Flow 24** | "Pure Java" UI. Server-side rendering with no separate frontend API needed. |
| **Database** | **PostgreSQL 16** | Robust relational data storage. |
| **ORM** | **Spring Data JPA** | Standardized data access (Hibernate implementation). |
| **AI Engine** | **Spring AI** | Native Java abstractions for LLM orchestration (replacing LangChain JS). |
| **Build Tool** | **Maven** | Declarative dependency management. |
| **Infra** | **Docker Compose** | Local container orchestration for Database & Vector Stores. |

## 3. Architecture Pattern
We utilize a **Service-Oriented Monolith** structure designed for eventual microservice extraction.

* **View Layer (`views`):** Vaadin Components (UI). Handles user interaction and input validation (`Binder`).
* **Service Layer (`service`):** Transactional business logic. The only layer allowed to talk to the Repository.
* **Data Layer (`entity` & `repository`):** JPA Entities mapping to Postgres tables and Interfaces for SQL execution.

## 4. Implementation Roadmap

### Phase 1: Genesis (Completed)
* [x] **Project Skeleton:** Spring Boot + Vaadin + Docker Compose configured.
* [x] **Database Connectivity:** PostgreSQL container auto-managed by Spring Boot.
* [x] **The Genome (Part 1):** `Organization` Entity and Repository created.
* [x] **Profile UI:** `OrganizationProfileView` implemented for creating new organizations.
* [x] **Version Control:** Git repository initialized and pushed to remote.

### Phase 2: The Ecosystem (Next Session)
*Objective: Model the internal hierarchy of the organism.*
* [ ] **Departments:** Create `Department` entity (One-to-Many with Organization).
* [ ] **Teams:** Create `Team` entity (One-to-Many with Department).
* [ ] **Hierarchy View:** Build a TreeGrid UI in Vaadin to visualize Org -> Dept -> Team structure.

### Phase 3: The Genome (Human & Role)
*Objective: Identity management and Access Control.*
* [ ] **User Identity:** Integrate **Spring Security**.
* [ ] **Employees:** Create `Employee` entity linking User credentials to specific Teams.
* [ ] **RBAC:** Implement Role-Based Access Control (Admin, Orchestrator, Contributor).
* [ ] **Authentication:** Replace hardcoded "admin-user" with real session data.

### Phase 4: The Canvas (Data Collection)
*Objective: Rebuild the core BCID assessment forms.*
* [ ] **Dynamic Forms:** Implement the 15-dimension Biomimetic Canvas.
* [ ] **Context Assessment:** Port the "Operational Context" PESTLE analysis logic.
* [ ] **Data Persistence:** Store canvas responses as structured JSONB or relational tables.

### Phase 5: Emergence (Analytics & Visualization)
*Objective: Visualizing the ecosystem.*
* [ ] **System Maps:** Implement interactive network graphs (using Vaadin-compatible JS wrappers or D3 integrations) to show the "OCEK" roles.
* [ ] **Word Clouds:** Server-side text analysis to generate organizational word clouds.
* [ ] **Dashboard:** Aggregated metrics view (The "Hive" Dashboard).

### Phase 6: Agentic Mesh (AI Integration)
*Objective: The "Brain" of the platform.*
* [ ] **Spring AI Setup:** Configure OpenAI / Ollama connections.
* [ ] **Orchestrator Agent:** Java bean that breaks down user goals.
* [ ] **Biomimetic Agent:** AI service that RAGs (Retrieves) biological patterns to solve organizational challenges.
* [ ] **Vector Database:** Add `pgvector` to Docker Compose for semantic search.

---

## 5. Development Guidelines

**Directory Structure:**
```text
src/main/java/org/bpm/ceelya/
├── CeelyaApplication.java  (Entry Point)
├── data/
│   ├── entity/             (Database Tables)
│   ├── repository/         (SQL Interfaces)
│   └── service/            (Business Logic)
├── views/                  (Vaadin UI Classes)
└── security/               (Auth Config - Future)