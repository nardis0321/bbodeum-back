리팩토링 목표
- 레이어 간의 참조 관계에서는 단방향 의존을 유지
- 계층 간 호출에서는 인터페이스를 통한 호출이 되도록

presentation
├─ Controller
├─ Dto
└─ Mapper
↓
application
└─ Facade
↓
domain
├─ Entity
├─ Service
├─ Command
├─ Criteria
├─ Info
├─ Reader
├─ Store
├─ Executer
└─ Factory
↑
infrastructure
├─ ReaderImpl
├─ StoreImpl
├─ Spring JPA
└─ RedisConnector