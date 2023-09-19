Spring Auth Cheat Sheet
Step 1: set up a user model and repo

Step 2: create a controller for that model

Step 3: UserDetailsServiceImpl implements UserDetailsService

Step 4: ApplicationUser implements UserDetails

Step 5: WebSecurityConfig extends WebSecurityConfigurerAdapter

Step 6: registration page

Step 7: login page


@GetMapping("/") public String get Index page
@GetMapping("/loginpage") get Index page
@GetMapping("/signup") get Index page