package api.endpoints;

public class Routes {
	
	
	static String base_URL ="https://petstore.swagger.io/v2";
	
	
	// Pet Module
	static String pet_post_URL =base_URL +"/pet";
	static String pet_get_URL =base_URL +"/pet/{PetId}";
	static String pet_update_post_URL =base_URL +"/pet/{PetId}";
	static String pet_delete_URL =base_URL +"/pet/{PetId}";
	
	// Store Module
	static String store_post_URL =base_URL +"/store/order";
	static String store_get_URL =base_URL +"/store/order/{OrderId}";
	static String store_delete_URL =base_URL +"/store/order/{OrderId}";
	
	// User Module
	static String user_post_URL =base_URL + "/user";
	static String user_get_URL =base_URL +"/user/{UserName}";
	static String user_put_URL =base_URL +"/user/{UserName}";
	static String user_delete_URL =base_URL +"/user/{UserName}";

}
