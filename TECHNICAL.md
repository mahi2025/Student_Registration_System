# Technical Documentation

## System Requirements

- Java Development Kit (JDK) 8 or higher
- Any Java IDE (Eclipse recommended)
- Minimum screen resolution: 1024x768

## Detailed Features

### Entry Panel

Purpose: Main entry point of the application

Components:

- Title: Student Registration Portal
- Two main buttons:
  - Student (for registration)
  - Admin (for administrative access)

Design:

- Light blue background
- Professional button styling
- Responsive layout

### Student Registration

Purpose: Register new students

Input Fields:

- Full Name (text field)
- ID (text field)
- Year (dropdown: 1-4)
- Department (dropdown with options):
  - Computer Science
  - Accounting
  - Marketing
  - Marketing Management

Validation:

- Checks for empty fields
- Shows error message if required fields are empty

Success Message:

- Displays all entered information
- Confirms successful registration

### Admin Login

Purpose: Secure access to administrative functions

Credentials:

- Username: hope
- Password: hope

Security:

- Password field masks input
- Invalid credentials message

### Admin Panel

Purpose: View and manage student registrations

Features:

- Tabular display of student information
- Sortable columns
- Refresh functionality
- Back button to return to main menu

Displayed Information:

- Full Name
- ID
- Year
- Department

## Class Structure

1. StudentRegistrationSystem

   - Main application class
   - Contains all GUI components and logic
   - Manages panel switching and data flow

2. Student
   - Inner class for student data
   - Properties:
     - fullName
     - id
     - year
     - department
   - Getter methods for all properties

## Data Management

- In-memory storage using ArrayList
- No persistent storage (data resets on application restart)
- Real-time updates in admin panel

## GUI Components

1. Layout Management

   - CardLayout for panel switching
   - GridBagLayout for form layouts
   - BorderLayout for admin panel

2. Styling
   - Custom colors for better visibility
   - Consistent font usage
   - Professional button styling
   - Responsive design

## Usage Guide

### For Students

1. Launch the application
2. Click Student button
3. Fill in the registration form:
   - Enter full name
   - Enter ID
   - Select year
   - Select department
4. Click Register
5. Review confirmation message
6. Click Back to return to main menu

### For Administrators

1. Launch the application
2. Click Admin button
3. Enter credentials:
   - Username: hope
   - Password: hope
4. View student registrations in table format
5. Use features:
   - Sort columns by clicking headers
   - Refresh to see new registrations
   - Back to return to main menu

## Error Handling

1. Empty Fields

   - Shows error message
   - Prevents registration
   - Prompts user to fill all fields

2. Invalid Login
   - Shows error message
   - Clears password field
   - Allows retry

## Future Enhancements

1. Data Persistence

   - Database integration
   - File-based storage

2. Additional Features

   - Student record editing
   - Record deletion
   - Search functionality
   - Export to Excel/PDF

3. Security
   - Password encryption
   - Multiple admin accounts
   - Session management

## Version History

Version 1.0
- Basic registration system
- Admin panel
- Student management
