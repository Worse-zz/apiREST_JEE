package fr.baillieul;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("departement")
public class DepartementResource {

	//@Inject
	//Appli appli;

	@EJB
	DepartementBean depbean ;
	
	@EJB
	UserBean usrbean ;


    public boolean tokenIsGood(String bearerToken) {
        if (bearerToken != null && !bearerToken.isEmpty()) {
            String token = bearerToken.split(" ")[1];
            try {
				if (usrbean.checkToken(token) != null) {
					return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }


	  @GET
	  @Path("/") 
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response get(@HeaderParam("Authorization") String bearerToken) {
		if (tokenIsGood(bearerToken)) { //boolean
			return Response.ok(depbean.getAllDep()).build(); 
		} else {
			return Response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
	  }

	  @GET
	  @Path("/getById/{id}")
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response getById(@HeaderParam("Authorization") String bearerToken, @PathParam("id") String name){
		if (tokenIsGood(bearerToken)) { //boolean
			return Response.ok(depbean.getById(name)).build(); 
		} else {
			return Response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}  
	  }
	
	  @POST
	  @Path("/addDep")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public Response post(@HeaderParam("Authorization") String bearerToken, Departement departement) {
		if (tokenIsGood(bearerToken)) { //boolean
			depbean.addDep(departement);
			return Response.ok().build();
		} else {
			return Response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}  		
	  }
	
	  @DELETE
	  @Path("/deleteDep/{name}")
	  public Response delete(@HeaderParam("Authorization") String bearerToken, @PathParam("name") String idDepartement) {
		if (tokenIsGood(bearerToken)) { //boolean
		  depbean.deleteDep(idDepartement);
		  return Response.ok().build();
		} else {
			return Response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
	  }
	
	  @PUT
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Path("/update")
	  public Response update(@HeaderParam("Authorization") String bearerToken,Departement Departement) {
		if (tokenIsGood(bearerToken)) { //boolean
			depbean.updateDepartementName(Departement);
			return Response.ok().build();
		} else {
			return Response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
	  }

	  @POST
	  @Path("/authent") //Création du compte
	  @Consumes(MediaType.APPLICATION_JSON)
	  public Response postAuthent(User user) {
		return Response.ok(usrbean.authent(user)).build();
	  }

	  @POST
	  @Path("/login") //Initialisation d'un token pour le compte
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response postLogin(User user) {
		return Response.ok(usrbean.login(user)).build();
	  }

	  @POST
	  @Path("/logout") //Suppression du token au compte
	  @Consumes(MediaType.APPLICATION_JSON)
	  public Response postLogout(User user) {
		return Response.ok(usrbean.logout(user)).build();
	  }

	  @POST
	  @Path("/logoutV2") //Suppression du token au compte
	  @Consumes(MediaType.APPLICATION_JSON)
	  public Response postLogoutV2(@HeaderParam("Authorization") String bearerToken) {
		if (tokenIsGood(bearerToken)) { //boolean
			usrbean.logoutV2(bearerToken);
			return Response.ok().build();
		} else {
			return Response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
	  }

}