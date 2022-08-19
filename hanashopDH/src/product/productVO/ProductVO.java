package product.productVO;

public class ProductVO {
   
   private int p_no;
   private String p_name;
   private int p_price;
   private String p_category;
   private String p_category2;
   private int p_stockS;
   private int p_stockM;
   private int p_stockL;
   private int p_count;
   private String p_image;
   
   
   public ProductVO() {}

   public ProductVO(int p_no, String p_name, int p_price, String p_category, String p_category2, int p_stockS,int p_stockM,int p_stockL,int p_count ,String p_image) {
//      super();
      this.p_no = p_no;
      this.p_name = p_name;
      this.p_price = p_price;
      this.p_category = p_category;
      this.p_category2 = p_category2;
      this.p_stockS = p_stockS;
      this.p_stockM = p_stockM;
      this.p_stockL = p_stockL;
      this.p_count=p_count;
      this.p_image=p_image;
   }

   
   public int getP_count() {
      return p_count;
   }

   public void setP_count(int p_count) {
      this.p_count = p_count;
   }
   
   public int getP_stockS() {
      return p_stockS;
   }

   public void setP_stockS(int p_stockS) {
      this.p_stockS = p_stockS;
   }

   public int getP_stockM() {
      return p_stockM;
   }

   public void setP_stockM(int p_stockM) {
      this.p_stockM = p_stockM;
   }

   public int getP_stockL() {
      return p_stockL;
   }

   public void setP_stockL(int p_stockL) {
      this.p_stockL = p_stockL;
   }

   public String getP_image() {
      return p_image;
   }

   public void setP_image(String p_image) {
      this.p_image = p_image;
   }

   public int getP_no() {
      return p_no;
   }

   public void setP_no(int p_no) {
      this.p_no = p_no;
   }

   public String getP_name() {
      return p_name;
   }

   public void setP_name(String p_name) {
      this.p_name = p_name;
   }

   public int getP_price() {
      return p_price;
   }

   public void setP_price(int p_price) {
      this.p_price = p_price;
   }

   public String getP_category() {
      return p_category;
   }

   public void setP_category(String p_category) {
      this.p_category = p_category;
   }
   
   public String getP_category2() {
      return p_category2;
   }

   public void setP_category2(String p_category2) {
      this.p_category2 = p_category2;
   }
   
   

   

}
