# checkout-system

## How to Run

### Prerequisites:
- Java 21
- Maven

### Compile and Run:

```bash
mvn clean compile
mvn exec:java          # to execute
mvn clean test         # very basic tests were added for service logic



# Example Session:

Enter items (A, B, C, D). Type 'TOTAL' to finish:
> A
Running total: £0.50
> B
Running total: £0.80
> A
Running total: £1.30
> A
Running total: £1.60
> B
Running total: £1.75
> TOTAL
Final total: £1.75


