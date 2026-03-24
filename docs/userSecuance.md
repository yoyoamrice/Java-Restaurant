User -> Registration UI: Submit registration form
Registration UI -> UserService: Validate input
UserService -> UsersTable: Check if email exists
UsersTable --> UserService: Email not found
UserService -> SecurityService: Hash password
SecurityService --> UserService: Return hash
UserService -> UsersTable: INSERT new user
UsersTable --> UserService: Return new user_id
UserService -> RolesTable: SELECT id WHERE role = 'default'
RolesTable --> UserService: Return role_id
UserService -> UserRolesTable: INSERT (user_id, role_id)
UserRolesTable --> UserService: Success
UserService -> Registration UI: Registration successful
Registration UI -> User: Display success message
