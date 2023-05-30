public class COMMENT {
    String content;

    String response;
    USER commentOwner;
    int ID;
    COMMENT(String content,USER user){
        this.content=content;
        this.response=null;
        this.commentOwner=user;
        this.ID=(int) SnappFood.randomNums2.next();
    }
    void setResponse(String response){this.response=response;}
    void getCommentResponse(){
        if(this.response==null) {
            System.out.println("there is no response for this comment.");
            System.out.println("---------------------------------------");
        }
        else{
            System.out.println("response : "+this.response);
            System.out.println("---------------------------------------");
        }
    }
    void editContent(String newContent){this.content=newContent;System.out.println("comment edited successfully.");}
}
