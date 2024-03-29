package com.springboot.security.Login;
import java.io.*;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class NaverLoginController {

    @Autowired
    NaverLoginDao loginDao;
    private String CLIENT_ID = "VHx6N_Qe2LFFoLvRnzkr"; //애플리케이션 클라이언트 아이디값";
    private String CLI_SECRET = "hcNYN2Mjth";

    @RequestMapping (value = "/naver")
    public String testNaver(HttpSession session, Model model) throws UnsupportedEncodingException, UnknownHostException {
        String redirectURI = URLEncoder.encode("http://localhost:8080/naver/callback", "UTF-8");
        SecureRandom random = new SecureRandom();
        String state = new BigInteger(130, random).toString();
        String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
        apiURL += String.format("&client_id=%s&redirect_uri=%s&state=%s",
                CLIENT_ID, redirectURI, state);
        session.setAttribute("state", state);

//        System.out.println(session.getId());
        model.addAttribute("apiURL", apiURL);
        return "test-naver";
    }
    /**
     * 콜백 페이지 컨트롤러
     * @param session
     * @param request
     * @param model
     * @return
     * @throws IOException
     * @throws ParseException
     */
    @RequestMapping(value = "/naver/callback")
    public String naverCallback(HttpSession session, HttpServletRequest request, Model model) throws IOException, ParseException {
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        String redirectURI = URLEncoder.encode("http://localhost:8080/naver/callback", "UTF-8");
        String apiURL;
        apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
        apiURL += "client_id=" + CLIENT_ID;
        apiURL += "&client_secret=" + CLI_SECRET;
        apiURL += "&redirect_uri=" + redirectURI;
        apiURL += "&code=" + code;
        apiURL += "&state=" + state;
        System.out.println("apiURL=" + apiURL);
        String res = requestToServer(apiURL);

//        String apiURL2 = "https://openapi.naver.com/v1/nid/me";
//        String headerStr = "Bearer " + accessToken;
//        String resUser = requestToServer(apiURL2,headerStr);


        if(res != null && !res.equals("")) {
            model.addAttribute("res", res);
            Map<String, Object> json = new JSONParser(res).parseObject();
            System.out.println(json);
            session.setAttribute("currentUser", res);
            session.setAttribute("currentAT", json.get("access_token"));
            session.setAttribute("currentRT", json.get("refresh_token"));
           // System.out.println( "token= "+json.get("access_token"));



           // Map<String, Object> row = new JSONParser(resUser).parseObject();
          //  Map<String, Object> user = (Map<String, Object>) row.get("response");


            int resultCode = 0;
            Map map= getProfileFromNaver(json.get("access_token").toString());

           Map<String, String> aRow = new HashMap<>();
           String user_Id = (String) map.get("id");
            session.setAttribute("currentUserId",user_Id); //현재 로그인 유저 아이디 세션에 저장!!
//            System.out.println("current userId = "+session.getAttribute("currentUserId")) ;

            if(loginDao.checkUserExist(user_Id)==0){
                String nickname = (String) map.get("nickname");
                String gender=(String)map.get("gender");
                aRow.put("user_id", user_Id);
                aRow.put("nickname", nickname);
                aRow.put("gender", gender);
                resultCode = loginDao.insertAnUserOAuth(aRow);
            }





        } else {
            model.addAttribute("res", "Login failed!");
        }
        return "test-naver-callback";
    }
    /**
     * 토큰 갱신 요청 페이지 컨트롤러
     * @param session
     * @param request
     * @param model
     * @param refreshToken
     * @return
     * @throws IOException
     * @throws ParseException
     */
    @RequestMapping("/naver/refreshToken")
    public String refreshToken(HttpSession session, HttpServletRequest request, Model model, String refreshToken) throws IOException, ParseException {
        String apiURL;
        apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=refresh_token&";
        apiURL += "client_id=" + CLIENT_ID;
        apiURL += "&client_secret=" + CLI_SECRET;
        apiURL += "&refresh_token=" + refreshToken;
        System.out.println("apiURL=" + apiURL);
        String res = requestToServer(apiURL);
        model.addAttribute("res", res);
        session.invalidate();
        return "test-naver-callback";
    }
    /**
     * 토큰 삭제 컨트롤러
     * @param session
     * @param request
     * @param model
     * @param accessToken
     * @return
     * @throws IOException
     */
    @RequestMapping("/naver/deleteToken")
    public String deleteToken(HttpSession session, HttpServletRequest request, Model model, String accessToken) throws IOException {
        String apiURL;
        apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=delete&";
        apiURL += "client_id=" + CLIENT_ID;
        apiURL += "&client_secret=" + CLI_SECRET;
        apiURL += "&access_token=" + accessToken;
        apiURL += "&service_provider=NAVER";
        System.out.println("apiURL=" + apiURL);
        String res = requestToServer(apiURL);
        model.addAttribute("res", res);
        session.invalidate();
        return "test-naver-callback";
    }
    /**
     * 액세스 토큰으로 네이버에서 프로필 받기
     * @param accessToken
     * @return
     * @throws IOException
     */

//    @ResponseBody
//    @RequestMapping("/naver/getProfile")
    public Map getProfileFromNaver(String accessToken) throws IOException, ParseException {
        // 네이버 로그인 접근 토큰;
        String apiURL = "https://openapi.naver.com/v1/nid/me";
        String headerStr = "Bearer " + accessToken; // Bearer 다음에 공백 추가
        String res = requestToServer(apiURL, headerStr);

        Map<String, Object> parsedJson = new JSONParser(res).parseObject();
        Map<String, Object> infoResp = (Map<String, Object>) parsedJson.get("response");
       // infoResp.get("email")
        return infoResp;
    }




    /**
     * 세션 무효화(로그아웃)
     * @param session
     * @return
     */
    @RequestMapping("/naver/invalidate")
    public String invalidateSession(HttpSession session) {
        session.invalidate();
        return "redirect:/naver";
    }
    /**
     * 서버 통신 메소드
     * @param apiURL
     * @return
     * @throws IOException
     */
    private String requestToServer(String apiURL) throws IOException {
        return requestToServer(apiURL, "");
    }
    /**
     * 서버 통신 메소드
     * @param apiURL
     * @param headerStr
     * @return
     * @throws IOException
     */
    private String requestToServer(String apiURL, String headerStr) throws IOException {
        URL url = new URL(apiURL);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        System.out.println("header Str: " + headerStr);
        if(headerStr != null && !headerStr.equals("") ) {
            con.setRequestProperty("Authorization", headerStr);
        }
        int responseCode = con.getResponseCode();
        BufferedReader br;
        System.out.println("responseCode="+responseCode);
        if(responseCode == 200) { // 정상 호출
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } else {  // 에러 발생
            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        }
        String inputLine;
        StringBuffer res = new StringBuffer();
        while ((inputLine = br.readLine()) != null) {
            res.append(inputLine);
        }
        br.close();
        if(responseCode==200) {
            return res.toString();
        } else {
            return null;
        }
    }
}

