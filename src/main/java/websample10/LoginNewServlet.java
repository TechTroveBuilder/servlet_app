package websample10;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Java入門 ログイン機能.
 */
@WebServlet("/LoginNewServlet")
public class LoginNewServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	/**
	 * コンストラクタ.
	 */
    public LoginNewServlet() {
        super();
    }

    /**
     * POSTメソッドでリクエストされた場合の処理.
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		// ① クリックされたボタンの判定
		// ①-1 ボタン名の文字化けを防ぐために文字コードを設定してから取得
		request.setCharacterEncoding("UTF-8");
		String btn = request.getParameter("submit");
		
		// ② 画面移動の準備
		HttpSession session = request.getSession();	// セッション
		RequestDispatcher rd;						// 画面の情報
		
		if("ログイン".equals(btn)) {
			
			// ③-1 クリックされたボタンが「ログイン」の場合はログイン処理を実施
			// ③-1-1 ログイン画面で入力されたIDとパスワードを取得
			String id  = request.getParameter("id");
			String pass= request.getParameter("pass");
			
			// ③-1-2 ユーザ情報をモデルに格納するために指示
			// ③-1-3 ログイン処理クラスをインスタンス化
			LoginDB login = new LoginDB();
			
			// ③-1-4 ID処理クラスに②-1-1で取得したIDを渡してユーザ情報をモデルに格納
			LoginUserBean bean = login.getUserData(id, pass);
			
			// ③-2 モデルの情報を判定
			if(bean != null) {
				
				// ③-2-1 モデルの情報が存在する場合はsetAttributeメソッドを使ってセッションに情報をセット
				// モデル（ユーザ情報）
				session.setAttribute("user_db", bean);
				// ログイン状態
				session.setAttribute("login_db", "login");
				// セッション有効時間（3分）
				session.setMaxInactiveInterval(180);
				// ③-2-2 つぎに表示させる画面（ビュー）を指定
				rd = request.getRequestDispatcher("./WEB-sample10/loginOK.jsp");
				
			} else {
				
				// ③-3 モデルの情報が存在しない（IDに紐づくユーザ情報がない）場合
				// ③-3-1 つぎに表示させる画面（ビュー）を指定
				rd = request.getRequestDispatcher("./WEB-sample10/loginNG.jsp");
			}
			
			rd.forward(request, response);
			
		} else {
			
			// ④ クリックされたボタンが「ログアウト」の場合はログアウト処理（セッションの破棄）を実施
			session.removeAttribute("login_db");
			session.removeAttribute("user_db");
			response.sendRedirect("./WEB-sample10/login.jsp");
		}
	}
}