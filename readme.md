Spring Auth Cheat Sheet
Step 1: set up a user model and repo

Step 2: create a controller for that model

Step 3: UserDetailsServiceImpl implements UserDetailsService

Step 4: ApplicationUser implements UserDetails

Step 5: WebSecurityConfig extends WebSecurityConfigurerAdapter

Step 6: registration page

Step 7: login page


@GetMapping("/") public String get Index page
@GetMapping("/loginpage") get login page
@GetMapping("/signup") get signup page
@postMapping(/profile) profile page 
@postMapping (/user/{id}) to show info for spasific user 



----------------------------------
# Steps to use codeFellowship website:
1.The user can be sign up , you should enter all information username ,password , first & last Name , date of birth and bio
2. the user should be login by user name and password 
3. now it appear a profile page the has information about the user and the user can be add new post and see his posts in the same page and all post link
4. the link All post it should show us all post for all users 

