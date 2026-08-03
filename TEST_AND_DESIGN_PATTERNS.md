# SAFAR Travel App - Design Patterns & Software Testing Report

This document details the **8 Software Design Patterns** and **5 Software Testing Methodologies** implemented in the SAFAR Travel Application codebase, divided between **Tanvir (`setanvir`)** and **Nazia (`rs-nazia`)**.

---

## 👥 Part 1: Tanvir's Responsibility (`setanvir`)

### 🎨 Design Patterns (4)
1. **Singleton Pattern** (`SingletonSession.kt`)
   - Thread-safe double-checked locking singleton for global user session management.
2. **Factory Method Pattern** (`BookingFactory.kt`)
   - `ServiceBookingFactory` creating `FlightBooking`, `HotelBooking`, and `TourBooking` polymorphically.
3. **Facade Pattern** (`BookingFacade.kt`)
   - `SafarBookingFacade` unifying inventory check, payment execution, and notification issuing under `bookCompleteTrip()`.
4. **Observer Pattern** (`ObserverPattern.kt`)
   - `BookingStatusSubject` notifying `UiNotificationObserver` and `AnalyticsObserver` when booking status changes.

### 🧪 Testing Methodologies (2)
1. **Cause-Effect Graphing Based Testing** (`CauseEffectDecisionTest.kt`)
   - Testing boolean cause/effect decision matrix for payment validation and approval rules.
2. **Parameterized Testing** (`ParameterizedPricingTest.kt`)
   - Testing discount calculation logic across multiple dataset parameters using `@RunWith(Parameterized::class)`.

---

## 👥 Part 2: Nazia's Responsibility (`rs-nazia`)

### 🎨 Design Patterns (4)
1. **Adapter Pattern** (`PaymentAdapter.kt`)
   - `BKashPaymentAdapter` and `NagadPaymentAdapter` adapting legacy third-party APIs to `PaymentProcessor` interface.
2. **Strategy Pattern** (`PricingStrategy.kt`)
   - `PricingStrategy` supporting `StandardPricing`, `SeasonalDiscountStrategy`, and `VipMemberStrategy` selected dynamically at runtime.
3. **Composite Pattern** (`TourComposite.kt`)
   - `TourPackageComponent` interface treating single activities (`SingleActivity`) and bundled packages (`BundleTourPackage`) uniformly.
4. **Iterator Pattern** (`DestinationIterator.kt`)
   - `CustomIterator` and `DestinationSequentialIterator` for custom collection traversal and price filtering.

### 🧪 Testing Methodologies (3)
1. **Whitebox Testing** (`WhiteboxAuthTest.kt`)
   - Whitebox testing inspecting internal structure, private state fields, and memory identity (`assertSame`).
2. **Mockito Unit Testing** (`MockitoBookingTest.kt`)
   - Mocking external subsystems (`InventorySubsystem`, `PaymentSubsystem`, `NotificationSubsystem`) to test business logic in isolation.
3. **Path Testing / Basis Path Coverage** (`PathCoverageBookingTest.kt`)
   - Testing all independent control flow paths (Cyclomatic Complexity branches) in booking calculations and facade failure paths.
