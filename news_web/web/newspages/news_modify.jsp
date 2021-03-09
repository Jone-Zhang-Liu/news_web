<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	  <base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<title>新闻中国</title>
   
    <link href="../css/admin.css" rel="stylesheet" type="text/css" />
</head>
<body>
  <div id="main">
      <div>
<%--	    <iframe src="console_element/top.html" scrolling="no" frameborder="0" width="947px" height="180px"></iframe>--%>
		  <%@ include file="console_element/top.html" %>
	  </div> 
      <div id="opt_list">
<%--      	<iframe src="console_element/left.html" scrolling="no" frameborder="0" width="130px"></iframe>--%>
		  <%@ include file="console_element/left.html" %>
      </div> 
	  <div id="opt_area">
	    <h1 id="opt_type"> 修改新闻： </h1>
		    <form action="" method="post">
		      <p>
		        <label> 主题 </label>
		        <select name="ntid">
		        	<option value='1' selected="selected">国内</option>
		        	<option value='2'>国际</option>
					<option value='3' selected="selected">军事</option>
		        	<option value='4'>体育</option>
		        </select>
		        <input type="hidden" name="nid" value="" />
		      </p>
		      <p>
		        <label> 标题 </label>
		        <input name="ntitle" type="text" class="opt_input" value="重庆涉黑富豪黎强夫妇庭审答辩言辞相互矛盾"/>
		      </p>
		      <p>
		        <label> 作者 </label>
		        <input name="nauthor" type="text" class="opt_input" value="news"/>
		      </p>
		      <p>
		        <label> 摘要 </label>
		        <textarea name="nsummary" cols="40" rows="3">重庆涉黑富豪黎强夫妇庭审答辩言辞相互矛盾</textarea>
		      </p>
		      <p>
		        <label> 内容 </label>
		        <textarea name="ncontent" cols="70" rows="10">昨天，黎强案继续开庭。黎强的亲属、保护伞等人相继过堂。黎强亲属均否认参加黑社会性质组织。黎强的妻子伍树芹几乎对所有关键问题都以“不知道”、 “不晓得”作答，对有些无法逃避的问题，她则推向黎强，力图将自己与整件涉黑案撇清干系。庭审中，公诉人向伍树芹着重询问了“保护伞”的有关问题。她和丈 夫黎强的说法多处矛盾。 　　伍树芹说，她认识姜春艳是因为姜的小孩，他们两家结了“亲家”，但他们之间没有金钱往来，逢年过节也没有红包。她的这一回答与黎强的说法相左。前天，黎强当庭承认他给姜春艳送过钱，但称这是“逢年过节给的红包，属于礼尚往来，不是行贿”。 　　伍树芹向法官交代，黎强要用现金时都是从她这里取。法官问：“那黎强给别人送钱你有没有跟着一起去？”“没有。”“他有没有跟你说过拿钱去干什 么？”“没有。”公诉人问：黎强有没有拿过20万元的支票送给肖庆隆？伍树芹说黎强确实拿过20万元的支票，但“拿到哪里去了我不知道，他也没给我讲”。 　　承包出租车的价格 　　两种说法差距巨大 　　黎强案的另一名“保护伞”蒋洪曾以低于市场价6.5万元的价格承包了渝强公司的出租车，检方将此认定为行贿。黎强第一天庭审时辩称，他们公司是 民营公司，出租车承包价没有硬性规定，自己想多少钱承包给别人都可以，因而此项行贿指控并不成立。然而，伍树芹当庭多次十分肯定地说，公司出租车承包价都 是24万元或25万元。 　　谈及曾安东 　　伍树芹紧张语塞 　　重庆市地税局稽查局局长曾安东(另案处理)帮助渝强公司偷逃税款一事，伍树芹称不知情。她说并不认识曾安东，一开始也不知道他是什么人。曾安东第二次到她家之后，她才知道曾是税务局的人。 　　公诉人问：“税务局的人到你家去干什么？” 　　“跟黎强和黎萍商量工作上的事情。”伍树芹吞吞吐吐地回答。 　　“他是税务局的人为什么要去你家商量工作？谈工作他们完全可以在单位谈啊？” 　　伍树芹一时语塞。公诉人再次重复问题，伍树芹沉默良久还是没回答上来。 　　三顶“保护伞”自身难保 　　蒋洪两年没查出非法营运 姜春艳否认帮助获取指标 　　黎强案的三顶“保护伞”开始过堂。重庆市沙坪坝区交通运输管理所原所长肖庆隆最先“登场”。 　　肖庆隆曾收受黎强48万元贿赂，为黎强吞并百余辆运营车牵线搭桥。此外，黎强17辆非法运营车受到肖庆隆的“照顾”长达两年之久。 　　肖庆隆交待，黎强给他28万元，“当时我没学好法律知识，以为自己是介绍人身份，钱算是介绍费”。关于保护非法运营车的问题，肖庆隆辩称，当年获知黎强非法运营的并非运管所一家单位，“开会不止10次，辖区内的交通部门都知道，不单是我们，都没人去查。” 　　蒋洪 　　两年没查出非法营运 　　重庆市巴南区公路运输管理所原副所长蒋洪是黎强的另一把“保护伞”。黎强的33辆客车在其辖区内非法运营长达两年。 　　蒋洪辩称，两年间都未查出问题是因为“不知道”、“没人举报”。他还称，当时并不知道黎强的38台车未取得道路运输许可证。 　　至于低价承租黎强的出租车，蒋洪称自己主动找黎强承包出租车是因为那台换下来的车暂时还没有车主运营，承租价是黎强自己说的，他本人并不知道黎强给的价格低于市场价。 　　姜春艳 　　否认帮助获取指标 　　重庆市委、市政府信访办公室原来访二处处长姜春艳是唯一一位女“保护伞”。检方指控她帮助黎强多次通过信访手段，使非法营运车辆转为合法。 　　但姜春艳说，她确实与同事一起参与接访过黎强涉案的几起上访，但这是领导安排她去的，是她的职责所在。接访后，黎强也确实找过她，想叫她“帮忙协调一下”，但她拒绝了，因为已经将这些情况转给交通主管部门处理了。 　　在一次会议结束后，姜春艳说她也向主管部门反映过出租车的一些不稳定因素，但这是出于工作责任心。“我觉得我最大的优点就是工作责任心强。” 　　姜春艳说，当时黎强找她时，作为黎强的朋友，她还曾劝黎强不要牵扯进矛盾很大的“鱼沙线”中去。 　　姜春艳坚决否认帮助黎强去获取营运指标的指控，“当时我还只是一个普通工作人员，我也没有这个资格”。 　　黎强累计送了1万多元钱财给姜春艳，对此，姜春艳说她承认自己受贿的事实，但她否认黎强送红包与线路指标有关。“当时我认为这是朋友间的往来，收了也没啥问题，但现在看来，我觉得作为一个党员，这也违反了纪律。” 　　伍树芹的口头禅 　　伍树芹说话声音比较小，她对指控的3项罪名都予以否认。起诉书指控称伍树芹是渝强公司的股东，是强劲公司的法人代表，她在两家公司负责财务工作。但伍树芹将此推得干干净净。 　　“我没有参加黑社会组织，因为我不知道这是黑社会组织。我也没有非法经营，因为我不知道。隐匿会计凭证的是，也是黎萍让我打包的，我不知道税务局要来检查。”从法庭询问一开始，“不知道”、“不晓得”似乎就变成了伍树芹的口头禅，她对几乎所有关键问题都以此作答。 　　伍树芹说她虽是强劲公司法人，但只是名义上的，公司签字都是黎强、何永红负责。“我只是公司的出纳，负责收公司的各种费用，经营管理我不参加，经营都是黎强、何永红说了算。”     对于隐匿会计凭证的指控，伍树芹也极力撇清干系。“当时是黎萍让我把手头的资料打包的，至于为什么要打包，我就不清楚了。”     “为什么你是股东，你还要听黎萍的呢？”公诉人问道。伍树芹说，因为老总在会上宣布过由黎萍负责公司所有财务。     伍树芹的一席话将责任往黎强身上推，黎强则大包大揽，将妻子洗刷得清清白白。他说伍树芹从不参与公司的决策，公司的事情她都不知道。 </textarea>
		      </p>
		      <p>
		        <label> 上传图片 </label>
		        <input name="file" type="file" class="opt_input"/>
		      </p>
		      <input type="submit" value="提交" class="opt_sub" />
		      <input type="reset" value="重置" class="opt_sub" />
		    </form>
		    <p></p>
		    <h1 id="opt_type">
				修改新闻评论：
			</h1>
		      <table width="80%" align="left">
		         <tr>
		           <td colspan="6"><hr />
		           </td>
		         </tr>
		   
			      	<tr>
			          <td> 留言人： </td>
			          <td>无花果</td>
			          <td> IP： </td>
			          <td>127.0.0.1</td>
			          <td> 留言时间： </td>
			          <td>2012-08-29 03：03：45.0</td>
			          <td><a href="#">删除</a></td>
			        </tr>
			        <tr>
			          <td colspan="6">法网恢恢，疏而不露！</td>
			        </tr>
			        <tr>
			          <td colspan="6"><hr />
			          </td>
			        </tr>
		      </table>
		  </div>
	  </div>
<%--	  <iframe src="console_element/bottom.html" scrolling="no" frameborder="0" width="947px" height="190px"></iframe>--%>
  <%@ include file="console_element/bottom.html" %>
  </div>
</body>
</html>	