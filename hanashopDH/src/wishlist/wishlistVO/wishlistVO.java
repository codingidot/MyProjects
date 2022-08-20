package wishlist.wishlistVO;

public class wishlistVO {


      private int w_no;
      private String w_id;
      private String w_pname;
      private int w_price;
      private int w_count;
      private String w_size;
      
    
      public wishlistVO(int w_no, String w_id, String w_pname, int w_price, int w_count, String w_size) {
         this.w_no = w_no;
         this.w_id = w_id;
         this.w_pname = w_pname;
         this.w_price = w_price;
         this.w_count = w_count;
         this.w_size= w_size;
     
         
      }

   public int getW_no() {
      return w_no;
   }

   public void setW_no(int w_no) {
      this.w_no = w_no;
   }

   public String getW_id() {
      return w_id;
   }

   public void setW_id(String w_id) {
      this.w_id = w_id;
   }

   
   public String getW_pname() {
      return w_pname;
   }

   public void setW_pname(String w_pname) {
      this.w_pname = w_pname;
   }

   public int getW_price() {
      return w_price;
   }

   public void setW_price(int w_price) {
      this.w_price = w_price;
   }

   public int getW_count() {
      return w_count;
   }

   public void setW_count(int w_count) {
      this.w_count = w_count;
   }

   public String getW_size() {
      return w_size;
   }

   public void setW_size(String w_size) {
      this.w_size = w_size;
   }
}