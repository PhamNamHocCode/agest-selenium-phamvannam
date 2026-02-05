package Railway;

public class RegisterAccount {
	public final String email;
	public final String password;
	public final String pip;
	
	public RegisterAccount(String email, String password, String pip) {
		this.email = email;
		this.password = password;
		this.pip = pip;
	}
	
	public RegisterAccount(RegisterAccount account) {
		this.email = account.email;
		this.password = account.password;
		this.pip = account.pip;
	}
}
