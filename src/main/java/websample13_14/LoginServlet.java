package websample13_14;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Java入門 ログイン機能.
 */
@WebServlet("/websample13_14/LoginServletShopping")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	/**
	 * コンストラクタ.
	 */
    public LoginServlet() {
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
		// TODO:2-⑥ ログイン画面または、ヘッダー画面からの値を判定する
		String btn = request.getParameter("submit");
		
		// ② 画面移動の準備
		HttpSession session = request.getSession();	// セッション
		RequestDispatcher rd;						// 画面の情報
		System.out.println("test01");
		if("ログイン".equals(btn)) {
			
			// ③-1 クリックされたボタンが「login」の場合はログイン処理を実施
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
				// ③-2-2 つぎに表示させる画面（ビュー）を指定
				rd = request.getRequestDispatcher("/ShoppingServlet");
				
			} else {
				
				// ③-3 モデルの情報が存在しない（IDに紐づくユーザ情報がない）場合
				// ③-3-1 つぎに表示させる画面（ビュー）を指定
				rd = request.getRequestDispatcher("../WEB-sample13_14/loginNG.jsp");
			}
			
			rd.forward(request, response);
			
		} else if("ログアウト".equals(btn)) {
			
			// ④ クリックされたボタンが「logout」の場合はログアウト処理（セッションの破棄）を実施
			session.removeAttribute("login_db");
			session.removeAttribute("user_db");
			response.sendRedirect("../WEB-sample13_14/login.jsp");
			
		} else if("履歴".equals(btn)) {
			
			// TODO:2-⑦セッションからユーザIDを取得して購入履歴を検索
			// ⑤クリックされたボタン（リンク）が「履歴」の場合は購入履歴画面に移動
			Shopping shopping = new Shopping();
			
			// セッションからユーザIDを取得
			String id = ((LoginUserBean)session.getAttribute("user_db")).getId();
			
			// 購入履歴を取得
			ArrayList<HistoryBean> bean = shopping.getHistory(id);
			
			// リクエストスコープにセットして画面移動
			request.setAttribute("history", bean);
			rd = request.getRequestDispatcher("../WEB-sample13_14/history.jsp");
			rd.forward(request, response);
		}
	}
	
	/**
	 * GETメソッドでリクエストされた場合の処理.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// 今回はdoPostで処理
		doPost(request, response);
	}
}