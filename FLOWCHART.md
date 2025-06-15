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
    G --> B
    E --> H{Check Credentials}
    H -->|Valid| I[Admin Panel]
    H -->|Invalid| E
    I --> B

## 2. Registration Process

flowchart TD
    A[Registration Form] --> B[Enter Full Name]
    B --> C[Enter ID]
    C --> D[Select Year]
    D --> E[Select Department]
    E --> F{Validate Fields}
    F -->|Empty Fields| G[Show Error]
    G --> A
    F -->|Valid| H[Create Student Object]
    H --> I[Add to Database]
    I --> J[Show Success Message]
    J --> K[Return to Entry Panel]


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


## 6. Error Handling Flow

flowchart TD
    A[User Input] --> B{Validation}
    B -->|Empty Fields| C[Show Error Message]
    B -->|Invalid Login| D[Show Invalid Credentials]
    B -->|Valid| E[Process Data]
    C --> F[Return to Form]
    D --> G[Return to Login]
    E --> H[Continue Process]

## 7. Student Data Structure

classDiagram
    class Student {
        -String fullName
        -String id
        -String year
        -String department
        +getFullName()
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
    C --> G[Title Label]
    C --> H[Student Button]
    C --> I[Admin Button]
    D --> J[Input Fields]
    D --> K[Combo Boxes]
    D --> L[Action Buttons]
    E --> M[Login Fields]
    E --> N[Login Buttons]
    F --> O[Data Table]
    F --> P[Control Buttons]


## 9. Event Handling Flow

flowchart TD
    A[User Action] --> B{Action Type}
    B -->|Button Click| C[Action Listener]
    B -->|Text Input| D[Input Validation]
    B -->|Combo Selection| E[Update Selection]
    C --> F[Panel Switch]
    D --> G[Data Processing]
    E --> H[Update Form]
    F --> I[Update UI]
    G --> J[Save Data]
    H --> K[Refresh Display]


## 10. System States

stateDiagram-v2
    [X] --> EntryPanel
    EntryPanel --> RegistrationPanel: Student Click
    EntryPanel --> LoginPanel: Admin Click
    RegistrationPanel --> EntryPanel: Back/Register
    LoginPanel --> EntryPanel: Back
    LoginPanel --> AdminPanel: Valid Login
    AdminPanel --> EntryPanel: Back
    AdminPanel --> AdminPanel: Refresh
    EntryPanel --> [X]: Close Application

