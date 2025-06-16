# Student Registration System Flowcharts

## 1. Main Application Flow

flowchart TD
A[Start Application] --> B[Entry Panel]
B --> C{User Choice}
C -->|Student| D[Registration Panel]
C -->|Admin| E[Login Panel]
D --> F{Validate Input}
F -->|Valid| G[Save Student Data]
F -->|Invalid| D
G --> H[Show Success Message]
H --> B
E --> I{Check Credentials}
I -->|Valid| J[Admin Panel]
I -->|Invalid| E
J --> B

## 2. Registration Process

flowchart TD
A[Registration Form] --> B[Enter First Name]
B --> C[Enter Last Name]
C --> D[Select Date of Birth]
D --> E[Select Gender]
E --> F[Enter ID]
F --> G[Select Year]
G --> H[Select Department]
H --> I{Validate Fields}
I -->|Empty Fields| J[Show Error]
J --> A
I -->|Invalid ID| K[Show ID Error]
K --> A
I -->|Valid| L[Create Student Object]
L --> M[Add to ArrayList]
M --> N[Show Success Message]
N --> O[Return to Entry Panel]

## 3. Admin Login Flow

flowchart TD
A[Login Form] --> B[Enter Username]
B --> C[Enter Password]
C --> D{Check Credentials}
D -->|Username: hope\nPassword: hope| E[Admin Panel]
D -->|Invalid| F[Show Error]
F --> A
E --> G[View Student Table]
G --> H[Refresh Data]
H --> G
G --> I[Sort Data]
I --> G

## 4. Data Management Flow

flowchart TD
A[Student Data] --> B[ArrayList Storage]
B --> C[Admin Panel Table]
C --> D{User Actions}
D -->|Sort| E[Sort Table Data]
D -->|Refresh| F[Update Table]
D -->|Back| G[Return to Entry]
E --> C
F --> C
G --> H[Entry Panel]

## 5. Panel Navigation Flow

flowchart TD
A[Entry Panel] --> B{User Selection}
B -->|Student| C[Registration Panel]
B -->|Admin| D[Login Panel]
C -->|Back| A
C -->|Register| E[Success Message]
E --> A
D -->|Back| A
D -->|Login| F[Admin Panel]
F -->|Back| A
F -->|Refresh| F
F -->|Sort| F

## 6. Error Handling Flow

flowchart TD
A[User Input] --> B{Validation}
B -->|Empty Fields| C[Show Error Message]
B -->|Invalid ID Format| D[Show ID Error]
B -->|Invalid Login| E[Show Invalid Credentials]
B -->|Valid| F[Process Data]
C --> G[Return to Form]
D --> G
E --> H[Return to Login]
F --> I[Continue Process]

## 7. Student Data Structure

classDiagram
class Student {
-String fullName
-String dateOfBirth
-String gender
-String id
-String year
-String department
+getFullName()
+getDateOfBirth()
+getGender()
+getId()
+getYear()
+getDepartment()
}

## 8. UI Component Hierarchy

graph TD
A[JFrame] --> B[CardLayout]
B --> C[Entry Panel]
B --> D[Registration Panel]
B --> E[Login Panel]
B --> F[Admin Panel]
C --> G[Logo]
C --> H[Title Label]
C --> I[Student Button]
C --> J[Admin Button]
D --> K[Input Fields]
D --> L[Date Spinner]
D --> M[Radio Buttons]
D --> N[Combo Boxes]
D --> O[Action Buttons]
E --> P[Login Fields]
E --> Q[Login Buttons]
F --> R[Data Table]
F --> S[Control Buttons]

## 9. Event Handling Flow

flowchart TD
A[User Action] --> B{Action Type}
B -->|Button Click| C[Action Listener]
B -->|Text Input| D[Input Validation]
B -->|Date Selection| E[Date Validation]
B -->|Combo Selection| F[Update Selection]
C --> G[Panel Switch]
D --> H[Data Processing]
E --> I[Date Processing]
F --> J[Update Form]
G --> K[Update UI]
H --> L[Save Data]
I --> L
J --> M[Refresh Display]

## 10. System States

stateDiagram-v2
[X] --> EntryPanel
EntryPanel --> RegistrationPanel: Student Click
EntryPanel --> LoginPanel: Admin Click
RegistrationPanel --> EntryPanel: Back
RegistrationPanel --> SuccessMessage: Register
SuccessMessage --> EntryPanel: Continue
LoginPanel --> EntryPanel: Back
LoginPanel --> AdminPanel: Valid Login
AdminPanel --> EntryPanel: Back
AdminPanel --> AdminPanel: Refresh/Sort
EntryPanel --> [X]: Close Application
