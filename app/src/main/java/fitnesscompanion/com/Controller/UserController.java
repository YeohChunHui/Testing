package fitnesscompanion.com.Controller;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import fitnesscompanion.com.Model.User;
import fitnesscompanion.com.R;

/**
 * Created by Kok Fung on 10/5/2017.
 */

public class UserController {
    private static final String TAG_RESULTS = "User";
    public static final String SERVER_ADDRESS = "https://taruc.000webhostapp.com/Fitness/";
    private ProgressDialog dialog;
    private Context context;
    private RequestQueue queue;

    public UserController(Context context) {
        this.context=context;
    }

    public interface VolleyCallCheckMail{
        void onSuccess(int count);
    }
    public interface VolleyCallCheckActive{
        void onSuccess(int active);
    }
    public interface VolleyCallgetUser{
        void onSuccess(User user);
    }


    public void checkMail(final VolleyCallCheckMail callBack,final String email) {
        queue = Volley.newRequestQueue(context);
        dialog = ProgressDialog.show(context,context.getString(R.string.sync),
                context.getString(R.string.loading_email),true,false);

        StringRequest postRequest = new StringRequest(Request.Method.POST, SERVER_ADDRESS+"checkEmail.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{
                    JSONArray jason = new JSONArray(response);
                    JSONObject jsonObject = jason.getJSONObject(0);
                    dialog.dismiss();
                    callBack.onSuccess(jsonObject.getInt("count"));

                } catch (JSONException e) {
                    Toast.makeText(context, "Error:" + e.getMessage(), Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error" + error.getMessage(), Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };
        queue.add(postRequest);
    }
    public void resetPass(final String email) {
        queue = Volley.newRequestQueue(context);
        dialog = ProgressDialog.show(context, context.getString(R.string.sync),
                context.getString(R.string.loading_recovery), true, false);
        StringRequest postRequest = new StringRequest(Request.Method.POST, SERVER_ADDRESS + "resetPass.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context,context.getString(R.string.recoverySuccess),Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error" + error.getMessage(), Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }

        }){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };
        queue.add(postRequest);
    }
    public void signUp(final User user) {
        queue = Volley.newRequestQueue(context);
        dialog = ProgressDialog.show(context,context.getString(R.string.sync),
                context.getString(R.string.loading_process),true,false);

        StringRequest postRequest = new StringRequest(Request.Method.POST, SERVER_ADDRESS + "signUp.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context,context.getString(R.string.signUpSuccess),Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error" + error.getMessage(), Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("name", user.getName());
                params.put("gender", String.valueOf(user.getGender()));
                params.put("email", user.getEmail());
                params.put("dob", user.getDob());
                params.put("pass", user.getPassword());
                params.put("image", user.getImage());

                return params;
            }
        };
        queue.add(postRequest);
    }
    public void loginIn(final VolleyCallCheckActive callBack ,final String email,final String pass) {
        queue = Volley.newRequestQueue(context);
        dialog = ProgressDialog.show(context,context.getString(R.string.sync),
                context.getString(R.string.loading_process),true,false);
        StringRequest postRequest = new StringRequest(Request.Method.POST, SERVER_ADDRESS + "loginIn.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONArray jason = new JSONArray(response);
                    JSONObject jsonObject = jason.getJSONObject(0);
                    dialog.dismiss();
                    callBack.onSuccess(jsonObject.getInt("active"));

                } catch (JSONException e) {
                    Toast.makeText(context, "Error:" + e.getMessage(), Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error" + error.getMessage(), Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("email", email);
                params.put("pass", pass);
                return params;
            }
        };
        queue.add(postRequest);
    }
    public void getUser(final VolleyCallgetUser callBack,final String email) {
        queue = Volley.newRequestQueue(context);
        dialog = ProgressDialog.show(context,context.getString(R.string.login_title),
                context.getString(R.string.login_msg),true,false);

        StringRequest postRequest = new StringRequest(Request.Method.POST, SERVER_ADDRESS + "getUser.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{
                    User user = new User();
                    JSONArray jason = new JSONArray(response);
                    JSONObject jsonObject = jason.getJSONObject(0);

                    user.setId(jsonObject.getInt("id"));
                    user.setName(jsonObject.getString("name"));
                    user.setGender(jsonObject.getInt("gender"));
                    user.setDob(jsonObject.getString("dob"));
                    user.setImage(jsonObject.getString("image"));
                    user.setHeight(jsonObject.getInt("height"));

                    callBack.onSuccess(user);
                    dialog.dismiss();

                } catch (JSONException e) {
                    Toast.makeText(context, "Error:" + e.getMessage(), Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error" + error.getMessage(), Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        })
        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };
        queue.add(postRequest);
    }
}
