package com.sys.action.school;

import com.sys.bean.school.ClassRoom;
import com.sys.bean.school.IndexImage;
import com.sys.bean.school.Message;
import com.sys.bean.school.Student;
import com.sys.service.school.ClassRoomService;
import com.sys.service.school.IndexImageService;
import com.sys.service.school.MessageService;
import com.sys.service.school.StudentService;
import com.sys.system.PagerModel;
import com.sys.web.action.BaseAction;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

/**
 * 前台页面请求处理
 */
@Controller
@Scope("prototype")
public class FrontAction extends BaseAction<Object>{

    private Student student;

    private File image;
    private String imageFileName;
    private String imageContentType;

    private Integer roomId;
    private String studentName;
    private String username;
    private String password;
    private Message message;
    @Autowired
    private StudentService studentService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private ClassRoomService classRoomService;
    @Autowired
    private IndexImageService indexImageService;

    private List<ClassRoom> roomList;
    private List<IndexImage> imageList;
    public String index(){
        imageList = indexImageService.findScrollDataNoPager();
        return "index";
    }
    public String registerInput(){
        roomList = classRoomService.findScrollDataNoPager();
        return "register";

    }
    public void checkUserName(){
         sendMessage(ServletActionContext.getResponse(),studentService.checkUserName(username)+"");
    }
    public String register() throws IOException {
        studentService.register(student);
        if(image != null){
            if(!checkFileType(imageContentType,imageFileName)) throw new RuntimeException("请上传图片文件");
            String path = ServletActionContext.getServletContext().getRealPath(student.getSavePath());
            imageFileName = UUID.randomUUID().toString()+imageFileName.substring(imageFileName.lastIndexOf("."));//生成UUID文件名
            File imageDir = new File(path);
            if(!imageDir.exists()) imageDir.mkdirs();//创建保存图片的文件夹
            FileUtils.copyFile(image, new File(path+"/"+imageFileName));
            student.setImage(imageFileName);
            studentService.update(student);
        }
        ServletActionContext.getRequest().getSession().setAttribute("student",student);
        return index();
    }
    public void login(){
        try {
            student = studentService.login(username,password);
            ServletActionContext.getRequest().getSession().setAttribute("student",student);
        } catch (Exception e) {
            e.printStackTrace();
            sendMessage(ServletActionContext.getResponse(),e.getMessage());
            return;
        }
        sendMessage(ServletActionContext.getResponse(),"success");
    }
    public String loginOut(){
        ServletActionContext.getRequest().getSession().removeAttribute("student");
        return index();
    }

    public String searchStudent(){
        LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
        orderBy.put("id", "desc");
        StringBuffer whereSql = new StringBuffer(" 1 = 1 ");
        List<Object> params = new ArrayList<Object>();
        if(studentName != null && !"".equals(studentName)){
            whereSql.append("and o.realname like ? ");
            params.add("%"+ studentName +"%");
        }
        if(roomId != null ){
            whereSql.append("and o.room.id = ? ");
            params.add(roomId);
        }
        pm = studentService.findScrollData(orderBy,whereSql.toString(),params.toArray());
        roomList = classRoomService.findScrollDataNoPager();
        setPageInfo();
        return "contact";
    }
    public String commentList(){
       Student student = (Student) ServletActionContext.getRequest().getSession().getAttribute("student");
        LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
        orderBy.put("id", "desc");
        StringBuffer whereSql = new StringBuffer(" 1 = 1 ");
        List<Object> params = new ArrayList<Object>();
            whereSql.append("and o.student.room.id = ? ");
            params.add(student.getRoom().getId());
        pm = messageService.findScrollData(orderBy,whereSql.toString(),params.toArray());
        setPageInfo();
        return "comment";
    }

    public String comment() throws Exception {
        Student student = (Student) ServletActionContext.getRequest().getSession().getAttribute("student");
        message.setStudent(student);
        messageService.save(message);
        return "addComment";
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        try {
            this.studentName = new String(studentName.getBytes("iso8859-1"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public StudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public ClassRoomService getClassRoomService() {
        return classRoomService;
    }

    public void setClassRoomService(ClassRoomService classRoomService) {
        this.classRoomService = classRoomService;
    }

    public List<ClassRoom> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<ClassRoom> roomList) {
        this.roomList = roomList;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public MessageService getMessageService() {
        return messageService;
    }

    public List<IndexImage> getImageList() {
        return imageList;
    }

    public void setImageList(List<IndexImage> imageList) {
        this.imageList = imageList;
    }

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }
}
