package websample13_14;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Java入門 商品一覧ページ処理クラス.
 */
@WebServlet("/websample13_14/ShoppingServlet")
public class ShoppingServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
      
	/**
	 * コンストラクタ.
	 */
    public ShoppingServlet() {
        super();
    }
    
    /**
     * POSTメソッドで呼び出された場合の処理.
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 商品一覧を取得
		Shopping shopping = new Shopping();
		ArrayList<ItemBean> itemList = shopping.getItem();
		
		// 商品一覧をリクエストスコープの属性にセット
		request.setAttribute("itemList", itemList);
		
		// 商品一覧画面に移動
		
		String back = request.getParameter("back");
		System.out.println(back);
		if("一覧に戻る".equals(back)) {
			RequestDispatcher rd = request.getRequestDispatcher("../WEB-sample13_14/itemList.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("./WEB-sample13_14/itemList.jsp");
			rd.forward(request, response);
		}
		
	}	
}