package hn.epharma.model;

public class JsonViews {
	public static class Common {

	}

	public static class CommandeWithClient extends Common {}

	public static class ClientWithCommand extends Common {}
	
	public static class CommandeWithLigne extends Common {}
	
	public static class LigneWithCommande extends Common {}
}
