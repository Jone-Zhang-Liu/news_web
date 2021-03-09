<%@ page import="org.apache.commons.fileupload.FileItem" %>
<%@ page import="org.apache.commons.fileupload.FileItemFactory" %>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory" %>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.fileupload.FileUploadBase" %><%--
  Created by IntelliJ IDEA.
  User: wuligang
  Date: 2021/2/22
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>上传控制页</title>
</head>
<body>
<%
    request.setCharacterEncoding("utf-8");
    //判断是否是文件上传表单(enctype="multipart/form-data")
    boolean multipartContent = ServletFileUpload.isMultipartContent(request);
    if (multipartContent) {//是
        //创建FileItemFactory对象
        FileItemFactory factory = new DiskFileItemFactory();
        //创建ServletFileUpload对象
        ServletFileUpload upload = new ServletFileUpload(factory);

        //设置上传文件的最大字节数为30kb
        upload.setSizeMax(1024 * 30);
        //解析form表单
        try {
            List<FileItem> items = upload.parseRequest(request);
            for (FileItem item : items) {
                if (item.isFormField()) {//是普通表单项
                    String fieldName = item.getFieldName();//name属性值
                    if ("uploader".equals(fieldName)) {
                        String value = item.getString("utf-8");//value属性值
                        out.print(fieldName + ":" + value);
                    }
                } else {//是文件域
                    String filename = item.getName();//上传的文件名
                    List<String> fileType = Arrays.asList("gif", "bmp", "jpg", "png", "jpeg");
                    String ext = filename.substring(filename.lastIndexOf(".") + 1);

                    if (!fileType.contains(ext)) {
                        out.print("上传失败，格式不对，只能是gif bmp jpg png jpeg格式");
                    } else {

                        //上传的真实路径
                        String realPath = application.getRealPath("/upload");
                        File uploadFile = new File(realPath);
                        if (!uploadFile.exists()) {//如果不存在就创建
                            uploadFile.mkdir();
                        }
                        //创建上传的File对象
                        File file = new File(realPath, filename);
                        //执行上传操作
                        item.write(file);
                        out.print("上传成功");
                    }
                }
            }
        } catch (FileUploadBase.SizeLimitExceededException e) {
            out.print("上传文件太大，最大不超过30kb");
        } catch (Exception e) {
            e.printStackTrace();
        }
    } else {
        out.print("表单类型错误，不能上传文件");
    }
%>
</body>
</html>
