function isPassport(){return/^http:\/\/passport\.tianya\.cn\/register|login_m\.jsp/.test(window.location.href)?!0:!1}function isFromApp(){var t=TY.param.getHrefParam();return t&&(/^[iac]$/i.test(t.f)||/^daily/i.test(t.f))?!0:!1}function actControl(){function t(t){TY.loadUrl(e[t].js,function(){e[t].cbk&&e[t].cbk(),TY.clickStat("act-"+e[t].id+"-加载")})}function n(){var n=Math.ceil(1e3*Math.random())%c;t(n)}for(var a=new Date,i=TY.param.getHrefParam().fromact||null,e=[{id:"baoxiang",name:"18周年预热-宝箱",js:"http://cms.tianyaui.com/cms_fragments/res/11847/js/TY_BX.js",begin:new Date(2016,9,9,9,30),end:new Date(2016,9,17,0,0),fun:function(){return $("#j-post-content").size()?!0:!1},cbk:function(){}},{id:"hongbao_uv",name:"uv转换-红包",js:"http://static.tianyaui.com/act/qhbuv-ph/js/main.js",begin:new Date(2016,9,9,9,30),end:new Date(2016,11,31,59,59),fun:function(){var t={funinfo:1,free:1,feeling:1,worldlook:1,develop:1,16:1,no04:1,travel:1,98:1,no11:1};if("undefined"==typeof bbsGlobal||!t[bbsGlobal.item])return!1;var n="uvhb",e=48e4,o=a.getTime(),s=__global.isOnline()?1:0,c=TY.cookie.get(n)||"",l=0,r=0,p=c.split(".");return p.length&&(l=parseInt(p[0],10)),2==p.length&&(r=p[1]),l&&l+e>o&&("hongbao_uv"!=i||0!=r)?!1:(TY.cookie.set(n,o+"."+s,null,null,1),!0)},cbk:function(){}}],o=!1,s=0;s<e.length;)a<e[s].begin||a>e[s].end||!e[s].fun()?e.splice(s,1):(e[s].id==i&&(o=s),s++);var c=e.length;o!==!1?t(o):c&&n()}TY.namespace("m"),TY.loadedModule("TY.m.nav"),TY.m.nav=function($){function getData(t,n,a,i){return t=t||"",n=n||{},a=a||function(){},i=i||!1,""==t?!1:(i||(n._t=(new Date).getTime()),-1==t.indexOf("?")&&(t+="?"),t+=$.param(n),void TY.loadUrl(t,function(){a&&$.isFunction(a)&&a()}))}function getDomain(){var t=document.domain,n=t.split("."),a=n.length;return t=a>=2?n[a-2]+"."+n[a-1]:"tianya.cn"}function getScrollTop(t){var n=t||document;return TY.os&&TY.os.ios?parseInt(window.scrollY,10):Math.max(n.documentElement.scrollTop,n.body.scrollTop)}function initDom(){var t=['<div class="ty-m-nav">','<header class="m-header" id="j-header">','<div class="m-bar f-cf">','<ul class="m-bar-left" id="ty_go_back">','<li class="a">','<a href="{goBackUrl}" class="icon-back" title="后退"></a>',"</li>","</ul>",'<ul class="m-bar-center" id="ty_logo">',1==Conf.navLevel?'<li class="title">天涯 · '+Conf.title+"</li>":2==Conf.navLevel?'<li class="title">'+Conf.title+"</li>":'<li class="logo"><a href="http://www.tianya.cn/m/" class="logo-img" title="天涯logo"><span class="f-vh">天涯社区</span></a>{title}</li>',"</ul>",'<ul class="m-bar-right user-info"></ul>',"</div>","</header>",'<nav class="m-nav "  id="ty_nav" >','<div class="nav-wrap {showNav}">',"</div>","</nav>",'<div class="nav-mask" id="nav_mask"></div>',"</div>"].join("");if(Conf.showTopNav){Conf.navFromHtml?2==Conf.navLevel&&Conf.title&&$("#ty_logo").html('<li class="title">'+Conf.title+"</li>"):$(Conf.app_id).prepend(t.TY_format({showNav:Conf.navOpen?"show":"",title:""!=Conf.title?"<span>·"+Conf.title+"</span>":"",goBackUrl:Conf.goBackUrl}));var n=location.href,a=['<li class="item menu '+(Conf.navOpen?"":"active")+'" id="ty_menu"  ><i class="nav-down"></i></li>',isOnline?'<li id="nav_user" class="item avatar '+(Conf.navOpen?"active":"")+'" ><a href="http://www.tianya.cn/m/my.jsp?comeURL='+n+'" title="个人中心"><img alt="头像" src="http://tx.tianyaui.com/logo/small/'+userId+'"></a></li>':'<li class="item '+(Conf.navOpen?"active":"")+'"><a href="http://passport.tianya.cn/login_m.jsp?fowardURL='+n+'" title="登录" class="ty-login"></a></li>'].join("");$("."+pre+" .user-info").html(a);var i=$("#ty_nav .nav-wrap");i.html(navsTpl);var e=$("#nav_up"),o=$("#nav_mask"),s=$("#nav_last");Conf.showHideIcon&&!Conf.navOpen&&(e.show(),s.hide()),Conf.clickDom2Hide&&Conf.navOpen&&(o.height($("body").height()),o.show()),!Conf.goBack&&Conf.navFromHtml&&$("#ty_go_back li").html('<a class="ty-search" href="http://search.tianya.cn/m/bbs.jsp?backUrl='+encodeURIComponent(location.href)+'" title="搜索"></a>'),Conf.maxWidth&&i.css({"max-width":parseInt(Conf.maxWidth,10)+"px"}),checkLocation(),Msg=new MyMsg,initEvent()}Conf.showBottomNav&&$("body").append(footTpl),Conf.renderCbk()}function initEvent(){function t(){r=getScrollTop(),l&&r>l?i?(n.trigger("click"),Conf.navOpen&&(Conf.navOPen=!1)):l=r=0:l=r}var n=$("#ty_menu"),a=$("."+pre+" .user-info li"),i=Conf.navOpen,e=$("#nav_mask"),o=$("#ty_nav"),s=$("#nav_up"),c=o.find(".nav-wrap");n.bind("click",function(){a.toggleClass("active"),i=!i,i?(c.show(),c.addClass("show"),e.height(l),o.css({position:"absolute"}),Conf.clickDom2Hide&&(e.height($("body").height()),e.show()),Conf.showHideIcon&&s.show(),"undefined"!=typeof BBSMain&&BBSMain.clickStat&&BBSMain.clickStat("导航展开")):(c.removeClass("show"),Conf.showHideIcon&&setTimeout(function(){s.hide()},500),Conf.clickDom2Hide&&e.hide())}),s.bind("click",function(){return n.trigger("click"),!1}),e.bind("tap touchend touchmove click",function(){return n.trigger("click"),!1});var l=0,r=0;if(Conf.moveHide&&(TY.os&&TY.os.ios?TY(window).bind("touchmove",t):$(window).bind("scroll",t)),Conf.goBack&&Conf.referrerBack)try{var p=location.pathname.match(/(\/m\/\w+)/),d=location.href,f=location.host+(p&&p[1]),u=document.referrer,h="javascript:history.go(-1)",m=/tianya.cn|hainan.net|tianyaclub.com/,b=/^http:\/\/bbs.tianya.cn\/m\/my_trace_list.jsp|^http:\/\/search.tianya.cn\/m\/bbs.jsp/,g={"www.tianya.cn/m":"javascript:history.go(-1)","bbs.tianya.cn/m/block":"http://www.tianya.cn/m/"};if(u.match(m)){if("undefined"!=typeof g[f])h=g[f];else if(d.match(b)){var v="referrer_"+p;u&&!u.match(/^http:\/\/bbs.tianya.cn\/m\/post-/)?window.localStorage[v]=u:u&&(h=window.localStorage[v])}}else h="http://www.tianya.cn/m/";TY("#ty_go_back .icon-back").attr({href:h})}catch(y){}o.delegate("a","click",function(){var t=$(this);"undefined"!=typeof BBSMain&&BBSMain.clickStat&&BBSMain.clickStat("导航-"+t.html())}),$("#j-header .m-bar a").click(function(){var t=$(this),n=t.attr("title");n&&"undefined"!=typeof BBSMain&&BBSMain.clickStat&&BBSMain.clickStat("导航-"+n)}),$("#nav_user a").click(function(){Msg.delMsg("t")})}function MyMsg(){function getCookie(type){type=type||"ty";var cn=argMap[type].cN,mo=argMap[type].mM,msg=__global.getCookie(cn);if(!msg)return null;if(-1!==msg.indexOf(ch)){for(var arr=msg.split(ch),obj={},i=0,l=mo.length;l>i;i++)obj[mo[i][0]]=+arr[i]||0;return obj}try{return eval(msg)}catch(e){return null}}function setCookie(t,n){n=n||"ty";for(var a=[(new Date).getTime(),t.uId||__global.getUserId()],i=argMap[n].cN,e=argMap[n].mM,o=2,s=e.length;s>o;o++)a.push(t[e[o][0]]||0);__global.setCookieNoEscape(i,a.join(ch),1,"/",domain,!1)}function showMsg(){var t=getCookie("ty")||{},n=getCookie("bbs")||{},a=(parseInt(t.t,10)||0)+(parseInt(n.t,10)||0)+(parseInt(t.uC,10)||0);a?numWrap.addClass(cls):numWrap.removeClass(cls)}function delMsg(t,n,a,i){i=i||"ty";var e=argMap[i].mM;if("t"==n){for(var o=2,s=e.length;s>o;o++)if(1==e[o][2]){var c=t[e[o][0]];t[e[o][0]]=a&&a>0&&c>=a?c-a:0}}else"bbs"==i&&(t.t=t.t-t[n]),t[n]=0;return setCookie(t,i),showMsg(),t}function setMsg(t,n,a){mo=argMap[n].mM;var i,e={};e.tS=a||(new Date).getTime(),e.uId=t.uId||__global.getUserId();for(var o=2,s=mo.length;s>o;o++)i=mo[o],e[i[0]]=parseInt(t[i[1]]||0,10),i[2]&&(e.t+=parseInt(t[i[1]]||0,10));argMap[n].hC=getCookie(),setCookie(e,n),showMsg()}function getMsg(t,n,a){t=t||"ty",getData(argMap[t].url,{},function(){var i=window[argMap[t].mO];if("undefined"!=typeof i&&i.success&&i.data&&(i.data.interval&&(loopTime=1e3*i.data.interval/2),setMsg(i.data,t,a),n&&n(),"ty"==t)){var e=getCookie("ty")||{};("undefined"==typeof e.t||e.t<1)&&getMsg("bbs")}})}function updateMsg(){var t=(new Date).getTime(),n=getCookie();n&&n.uId==userId&&t<parseInt(n.tS,10)+loopTime?showMsg():getMsg("ty",null,t),setTimeout(function(){updateMsg()},loopTime)}if(isOnline){var cookieName="ty_msg",bbsCookieName="bbs_msg",ch="_",historyCookie=null,msgObj="msgObj",msgMap=[["tS","tS",0],["uId","userId",0],["t","t",1],["fC","fanCount",1],["ryC","replyCount",1],["seC","shareCount",1],["rtC","requestCount",1],["uC","userCount",0],["ssC","sysCount",1],["amC","atMeCount",0],["aC","approveCount",1],["dsC","daShanfCount",0],["wlC","weilunCount",1]],bbsMsgObj="bbsMsgObj",bbsMsgMap=[["tS","tS",0],["uId","userId",0],["t","t",1],["aNC","attention_notice_count",1],["rNC","reply_notice_count",0],["cNC","comment_notice_count",0]],loopTime=3e4,url="http://www.tianya.cn/api/tw?var=msgObj&method=messagecount.ice.select&params.userId="+userId+"&",bbsUrl="http://bbs.tianya.cn/api?method=bbs.api.getUserNoticeCount&var=bbsMsgObj&",argMap={ty:{cN:cookieName,mO:msgObj,mM:msgMap,url:url,hC:null},bbs:{cN:bbsCookieName,mO:bbsMsgObj,mM:bbsMsgMap,url:bbsUrl,hC:null}},numWrap=$("."+pre+" .user-info li"),div=$("#j-user"),cls="u-reddot";div.click(function(){return delMsg(getCookie("ty")||{},"t"),delMsg(getCookie("bbs")||{},"t"),!0}),updateMsg();var wait=1;this.getMsg=function(t,n){var a=getCookie();return a&&"undefined"!=typeof a[t]?(n&&n(a[t]),a[t]):(wait--&&arguments.callee(t,n),!1)},this.delMsg=function(t,n,a){n||(obj=getCookie(),delMsg(obj,t,a))}}}function checkLocation(){function t(t,n){n>o&&(o=n,c.removeClass(s),t.addClass(s))}var n="http://"+location.hostname,a=location.href,i=Conf.app_str,e="",o=0,s=Conf.sel_class,c=$(".ty-m-nav .nav-wrap a");c.each(function(o,s){s=$(s),e=s.attr("href"),i&&-1!=i.indexOf(s.attr("appstr"))&&t(s,1),0==e.indexOf(n)&&t(s,2),a==e&&t(s,3)})}var pub={},Conf={app_str:"",app_id:"body",title:"",navLevel:0,navOpen:!1,sel_class:"active",maxWidth:0,goBack:!1,referrerBack:!1,goBackUrl:"javascript:history.go(-1)",showHideIcon:!0,moveHide:!1,clickDom2Hide:!1,showBottomNav:!0,showTopNav:!0,renderCbk:function(){},navFromHtml:!1},navsTpl=['<div class="m-nav-row">','<a class="active" href="http://www.tianya.cn/m/" appstr="shouye">首页</a>','<a href="http://bbs.tianya.cn/m/block.jsp" appstr="bbs">论坛</a>','<a href="http://focus.tianya.cn/g" appstr="focus">聚焦</a>','<a href="http://groups.tianya.cn/" appstr="groups">部落</a>','<a href="http://blog.tianya.cn/m/" appstr="blog">博客</a>',"</div>",'<div class="m-nav-row">','<a href="http://wenda.tianya.cn/m/" appstr="wenda">问答</a>','<a href="http://book.tianya.cn/m/" appstr="ebook">文学</a>','<a href="http://yy.tianya.cn/" appstr="game">游戏</a>','<a href="http://jyj.tianya.cn/wp/index.shtml" appstr="wpan">微盘</a>','<a href="http://shang.tianya.cn/rank/m/dsRanking.do?from=t&type=1" style="display:;" id="nav_last" appstr="shang">打赏</a>','<div class="nav-up" style="display:none;" id="nav_up"></div>',"</div>"].join(""),footTpl=['<div class="ty-m-foot">','<div class="m-pc">','<a href="http://www.tianya.cn/m/">触屏版</a>','<a href="http://www.tianya.cn/login.html" id="j-to-web">电脑版</a>','<a href="http://bbs.tianya.cn/m/weixin.html?isappinstalled=0&tianya=1&openurl=http://3g.tianya.cn/dl/tianya.jsp">客户端</a>',"</div>",'<div class="copyright">','<a href="http://service.tianya.cn/m/html/index.html" id="j-feedback">给天涯提建议</a>',"<small>tianya.cn  琼B2-20060032</small>","</div>","</div>"].join(""),pre="ty-m-nav",isOnline=__global.isOnline(),userId=isOnline&&__global.getUserId()||null,Msg=null,domain=getDomain();return pub.init=function(t){return __global.isInTYAPP()?void TY("header").hide():($.extend(Conf,t),void initDom())},pub}(TY);var BBSMain={};BBSMain.maskShow=function(t,n,a,i,e){function o(){var t=$("body").height();c.height(t),a&&a(!0),c.show()}function s(t){c.hide(),a&&a(!1,t)}var c=$("#"+t);if(!c.size()){var l='<div class="func-mask '+(i||"")+'" id="'+t+'"></div>';TY("body").append(l),c=TY("#"+t)}c.unbind("click").bind("click",function(t){return s(t),!1}),e||c.bind("touchmove",function(){return!1}),this.hide=function(){s()},this.show=function(){o()}},BBSMain.QucickFun=function(t){function n(t,n){t=t||50,setTimeout(function(){a(c.get(0),"translate3d(0px,0px,0px) scale(0.2)"),c.removeClass(v),n||u.hide(),l.show()},t)}function a(t,n){"use strict";var a=t.style;a.webkitTransform=a.MsTransform=a.msTransform=a.MozTransform=a.OTransform=a.transform=n}function i(t,a,i,e){c.find(t).bind("click",function(){var t=$(this);return BBSMain.clickStat(t.find("span").html()),t.hasClass(p)||(a&&a($(this)),n(e)),i?i:!1})}function e(){l.addClass("post-func-close-s"),e=null}var o=['<div class="post-func" id="post_more" style="">','<ul id="circle_box">',"{items}","</ul>",'<div class="i-close" id="post_close_btn"></div>','</div><div class="post-func-close" id="post_more_btn"></div>'].join(""),s={tpl:['<li><a href="#" class="i-top"><span>页头</span></a></li>','<li><a href="http://search.tianya.cn/m/bbs.jsp?backUrl='+location.href+'" class="i-search"><span>搜索</span></a></li>','<li><a href="#js-footer" class="i-tail"><span>页尾</span></a></li>','<li><a href="http://bbs.tianya.cn/m/my_trace_list.jsp" class="i-history"><span>足迹</span></a></li>'].join(""),firstFunc:function(){},openCbk:function(){}};$.extend(s,t),$("body").append(o.TY_format({items:s.tpl}));var c=$("#post_more"),l=$("#post_more_btn"),r=$("#post_close_btn"),p="i-off",d=c.find("li").size(),f=360/d,u=new BBSMain.maskShow("post_mask",c,function(t){t||n(0,!0)}),h=$(window).width(),m=$("body").width(),b=-1*(m/2-125-10),g=0,v="post-func-open",y=10;fisrtShow=!0,h>m&&(y+=(h-m)/2,l.css({right:y+"px"}),c.css({right:y+"px"})),c.addClass("post-func-"+d),c.find("span").each(function(t,n){a(n,"rotate("+-f*t+"deg)")}),l.bind("click",function(){return a(c.get(0),"translate3d("+b+"px,"+g+"px,0px) "),c.addClass(v),fisrtShow&&(fisrtShow=!1,s.firstFunc()),u.show(),s.openCbk(),l.hide(),BBSMain.clickStat("圆点展开"),!1}),r.bind("click",function(){return n(),l.show(),!1}),$("body").bind("click touchend",function(){e&&e()}),i(".i-top",function(){window.scrollTo(0,0)}),$("#js_2_bottom").size()||$("body").append('<a name="js-footer" id="js_2_bottom"></a>'),i(".i-tail",null,!0),this.bind=function(t,n,a,e){i(t,n,a,e)},this.hide=n,this.mask=u},BBSMain.storage=function(t,n,a){var i=!1,a=a||0,e=(new Date).getTime();try{localStorage.test=1,i=window.localStorage}catch(o){i=!1}if(i){var s=null;if(!n){var c=i[t];return c?(s=JSON.parse(c),s&&s.d>=e?s.v:null):null}s={v:n,d:e+1e3*a*3600*24},i[t]=JSON.stringify(s)}else{if(!n)return TY.cookie.get(t);TY.cookie.set(t,n,location.host,"/",a)}},BBSMain.Trace=function(){function t(t){try{var n=c[t];return n?(n=JSON.parse(n),TY.isArray(n)&&n.length?n:!1):!1}catch(a){return!1}}function n(n,a,i){var e=t(n),o=[];if(e)for(var l=0,r=e.length;r>l&&s>l;l++){var p=e[l];i(p,a)&&o.push(p)}return o.unshift(a),c[n]=JSON.stringify(o),!0}function a(t){var a=n(e,t,function(t,n){return t.a!=n.a||t.i!=n.i});return a&&i({id:t.i,name:t.b,t:t.t}),a}function i(t){return n(o,t,function(t,n){return t.id!=n.id})}var e="s_trace",o="footstep",s=50,c=!1;try{localStorage.test=1,c=window.localStorage}catch(l){return c=!1,!1}this.addArticle=function(t){return c?void a(t):!1},this.getArticle=function(){var n=t(e);return n},this.getBlock=function(){var n=t(o);return n},this.addBlock=function(t){i({id:t.i,name:t.b,t:t.t,fb:t.fb||0})}},BBSMain.showPop=function(t){function n(){o.remove(),a.closeCbk()}var a={hd:"",bd:"",ft:"",height:200,width:280,skinClass:"",renderCbk:function(){},closeCbk:function(){}};$.extend(a,t);var i="bbs-pop-"+(new Date).getTime(),e=['<div class="bbs-com-pop {skinClass}" id="'+i+'" style="width:{width}px;height:{height}px;margin-top:{mt}px;margin-left:{ml}px" >','<span class="close-btn close">×</span>','<div class="hd">{hd}</div>','<div class="bd">{bd}</div>','<div class="ft">{ft}</div>',"</div>"].join("");a.mt=-1*a.height/2,a.ml=-1*a.width/2,TY("body").append(e.TY_format(a));var o=TY("#"+i),s=new BBSMain.maskShow("pop_mask",o,function(t,a){t||n(a)},"dark-mask");s.show(),a.renderCbk(o),o.delegate(".close","click",function(){n(),s.hide()})},BBSMain.showLoginTips=function(t,n,a,i){function e(){f--&&"undefined"==typeof clickPartLink?setTimeout(e,500):f&&(TY("#login_tip_btn").trigger("click"),BBSMain.clickStat(n+"提示-展示"))}function o(){TY("#login_tip_nomore:checked").size()?(TY("#login_tip_close_n_btn").trigger("click"),BBSMain.clickStat(n+"提示-关闭-再展示"),TY.cookie.set(p,1)):(TY("#login_tip_close_btn").trigger("click"),BBSMain.clickStat(n+"提示-关闭")),h.remove()}if(n=n||"登录",t=t||function(){return!0},a=a||"登录"==n?'<label><input type="checkBox" id="login_tip_nomore" />不再提示</label>':"此功能需登录才能使用哦~",__global.isOnline()||!t())return!1;var s=i?i:"http://"+location.host+location.pathname+"?st="+(TY.os&&TY.os.ios?parseInt(window.scrollY,10):Math.max(document.documentElement.scrollTop,document.body.scrollTop)||0),c=encodeURIComponent(s),l=window.navigator.userAgent.toLowerCase(),r="micromessenger"==l.match(/MicroMessenger/i)?!0:!1,p="m-login",d=TY.cookie.get(p)?!1:!0,f=20,u=['<div class="bbs-login-tip" id="bbs_login_div">','<a href="http://bbs.tianya.cn/m/login-uv/show.html" id="login_tip_btn" style="display:none;" onclick="typeof(clickPartLink)!=\'undefined\'&&clickPartLink(event,\'stat\',\'m-'+n+"提醒-展示');return false;\"></a>",'<a href="http://bbs.tianya.cn/m/login-uv/close.html" id="login_tip_close_btn" style="display:none;" onclick="typeof(clickPartLink)!=\'undefined\'&&clickPartLink(event,\'stat\',\'m-'+n+"提醒-关闭'); return false;\"></a>",'<a href="http://bbs.tianya.cn/m/login-uv/close-no-show.html" id="login_tip_close_n_btn" style="display:none;" onclick="typeof(clickPartLink)!=\'undefined\'&&clickPartLink(event,\'stat\',\'m-'+n+"提醒-关闭-不再提醒'); return false;\"></a>",'<div class="hd">','<a class="closeBtn" href="#">×</a>','<p class="b">登录天涯  发现精彩</p>','<p class="s">与1亿涯友一起演绎精彩网络人生</p>',"</div>",'<div class="bd">',"<p>"+a+"</p>",'<div class="other-login">','<a href="https://graph.qq.com/oauth2.0/authorize?client_id=100253980&response_type=code&redirect_uri='+encodeURIComponent("http://passport.tianya.cn/login/qq.do?client=pc&fowardURL="+s)+"\" onclick=\"typeof(clickPartLink)!='undefined'&&clickPartLink(event,'stat','m-登录提醒-QQ登录');\"  class=\"btn qq-btn\">QQ登录</a>",r?'<a href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx160fa51254add6be&redirect_uri='+encodeURIComponent("http://passport.tianya.cn/login/weixin.do?client=touch&fowardURL="+s)+"&response_type=code&scope=snsapi_login#wechat_redirectt\" onclick=\"typeof(clickPartLink)!='undefined'&&clickPartLink(event,'stat','m-登录提醒-微信登录');\"  class=\"btn wx-btn\">微信登录</a>":"",'<a href="https://api.weibo.com/oauth2/authorize?client_id=482040646&response_type=code&with_offical_account=1&redirect_uri='+encodeURIComponent("http://passport.tianya.cn/login/sinaweibo.do?client=pc&fowardURL="+s)+"\" onclick=\"typeof(clickPartLink)!='undefined'&&clickPartLink(event,'stat','m-登录提醒-微博登录');\"  class=\"btn wb-btn\">微博登录</a>","</div>","</div>",'<div class="ft">','<a href="http://passport.tianya.cn/login_m.jsp?fowardURL='+c+"\" class=\"login btn btn-blue\" onclick=\"typeof(clickPartLink)!='undefined'&&clickPartLink(event,'stat','m-"+n+"提醒-登录');\" >已有账号，马上登录</a>","</div>","</div>"].join("");if(!d&&"登录"==n)return!1;TY("body").append(u);var h=TY("#bbs_login_div"),m=new BBSMain.maskShow("login_mask",h,function(t,n){t||o(n)},"dark-mask");m.show(),e(),h.find(".closeBtn").click(function(){return m.hide(),!1}),h.find(".login").click(function(){BBSMain.clickStat(n+"提示-登录")}),h.find(".login").click(function(){BBSMain.clickStat(n+"提示-登录")}),h.find(".regist").click(function(){BBSMain.clickStat(n+"提示-注册")}),h.find(".other-login").delegate("a","click",function(){BBSMain.clickStat(n+TY(this).html())})},BBSMain.clickStat=function(t){var n="m-v3-",a="",i=location.host+location.pathname,e={index:/^www.tianya.cn\/m\/$/,bbs:/^bbs.tianya.cn\/m\/$/,other:/\/m\/(\w+)/},o=window.navigator.userAgent.toLowerCase(),s="micromessenger"==o.match(/MicroMessenger/i)?!0:!1,c="",l=[];for(var r in e)l=i.match(e[r]),l&&(a=r),"other"==r&&l&&(a=l[1]);c=n+a+"-"+t+(s?"-wx":""),"undefined"!=typeof _hmt&&t?_hmt.push(["_trackEvent",c,"click"]):"undefined"==typeof _hmt&&setTimeout(function(){"undefined"!=typeof _hmt&&_hmt.push(["_trackEvent",c,"click"])},1e3),t&&"undefined"!=typeof TY.clickStat&&TY.clickStat("pm-bbs-"+c)},BBSMain.goWeb=function(){var t=location.href,n="http://www.tianya.cn/login.html";window.bbsGlobal&&bbsGlobal.ToWeb&&"true"===bbsGlobal.ToWeb?n=t.replace("/m/","/"):t.match(/^http:\/\/bbs.tianya.cn/)&&(n="http://bbs.tianya.cn"),$("#j-to-web")[0]&&$("#j-to-web").attr({href:n,target:"_blank"})},BBSMain.msg=function(t,n,a,i){$('<div id="j-msg" class="u-msg"><p></p></div>').appendTo("body").addClass("u-msg-"+t).show().find("p").html("<i></i>"+n),setTimeout(function(){$("#j-msg").remove(),i&&i()},a||2e3)};var msg=BBSMain.msg,maskShow=BBSMain.maskShow,QucickFun=BBSMain.QucickFun,checkVip={check:function(){return"undefined"!=typeof bbsGlobal&&(1==bbsGlobal.adblock||"undefined"!=typeof bbsGlobal.authorId&&bbsGlobal.authorId==__global.getUserId())?($(".gg-item").hide(),void BBSMain.clickStat("无广告")):void __global.isVipUser(function(t){1!=t?TY.loadUrl("http://static.tianyaui.com/global/m/v3/static/js/mod/extra_a10d87f.js",function(t){t||BBSMain.clickStat("屏蔽广告")}):1==t&&$("#j-post-content").size()&&$(".gg-item").hide()})}},bbsShowApp={init:function(t){t=t||0,__global.isInTYAPP()||this.addTips(this.isFrom()||t)},addTips:function(t){function n(){var n='<div class="app-tips app-tips-fixed f-cf"><div class="app-tips-right f-fr"><a href="'+e+'" class="u-btn u-btn-blue">立即'+a+'</a></div><div class="app-tips-left"><div class="app-logo '+r+'"></div><p><a href="'+e+'">'+i+"</a><br>"+p+'</p></div><a class="app-tips-close" href="javascript:;">&times;</a></div>';$("body").append(n).addClass("formapp"),BBSMain.clickStat(i+"-展示-"+t),$(".app-tips-fixed").delegate("a","click",function(){BBSMain.clickStat($(this).hasClass("app-tips-close")?i+"-关闭":i+"-"+a)}),$(".app-tips-close").click(function(){$(this).parent().remove(),$("body").css("padding-bottom","0"),$("#j-gotop").css("bottom","0")})}var a,i,e,o,s,c,l,r="add-logo-default",p="全球华人兴趣社交平台",d=navigator.userAgent.match(/android/gi),f=navigator.userAgent.match(/iphone|ipod/gi),u=navigator.userAgent.match(/ipad/gi),s=c=l=0;switch("undefined"!=typeof bbsGlobal&&(s=bbsGlobal.item||0,c=bbsGlobal.artId||0,l=bbsGlobal.page||0),t){case 0:break;case 1:a="打开",i="天涯社区",e="http://bbs.tianya.cn/m/weixin.html?isappinstalled=0&tianya=1&openurl=http://3g.tianya.cn/dl/tianya.jsp",n();break;case 2:a="打开",i="天涯社区",!f&&!u||d?d&&(o="tianya://post?catid="+s+"&aticleid="+c+"&page="+l):o="tianya://reply?categoryId="+s+"&noteId="+c+"&pageNo="+l+"&onlyOpen=1&version=1",e="http://bbs.tianya.cn/m/weixin.html?isappinstalled=1&tianya=1&openurl="+encodeURIComponent(o),n();break;case 3:a="打开",i="天涯社区",e="http://bbs.tianya.cn/m/weixin.html?isappinstalled=0&tianya=1&openurl=http://3g.tianya.cn/dl/tianya.jsp",n();break;case 4:r="app-logo-daily",a="打开",i="天涯日报",p="天涯品质阅读",!f&&!u||d?d&&(o="tianyadaily://notecontent?categoryId="+s+"&noteId="+c+"&pageNo="+l):o="tianyadaily://openpostdetail?categoryId="+s+"&noteId="+c+"&pageNo="+l,e="http://bbs.tianya.cn/m/weixin.html?isappinstalled=1&tianya=1&openurl="+encodeURIComponent(o),n();break;case 5:r="app-logo-daily",a="打开",i="天涯日报",p="天涯品质阅读",e="http://bbs.tianya.cn/m/weixin.html?isappinstalled=0&openurl=http://3g.tianya.cn/dl/daily.jsp",n()}},isFrom:function(){var t=0,n=document.referrer,a=TY.param.getHrefParam(),i=a.f,e=a.isappinstalled;return t=i&&/^[iac]$/i.test(i)?e&&1==e?2:3:i&&/[daily]$/i.test(i)?e&&1==e?4:5:""==n||""!=n&&-1==n.indexOf(".tianya.cn")?1:0}};"undefined"==typeof TY.clickStat&&(TY.clickStat=function(t){var n="http://collect.tianya.cn/newAccess.jsp?p="+t,a=document.createElement("img");a.src=n,a.onload=a.onerror=function(){a=null}});var Trace=BBSMain.Trace,goWeb=BBSMain.goWeb;$("body").ready(function(t){checkVip.check(),Trace(),t("#j-post-content").size()||t("#pose").size()||new QucickFun,t.loadUrl("http://static.tianyaui.com/global/ty/stat/stat_20080313.js"),t.loadUrl("http://hm.baidu.com/h.js?bc5755e0609123f78d0e816bf7dee255");var n=null;(n=t("#j-bbs-hotpost"))&&n&&n.size()&&n.delegate(".rank-list a","click",function(){BBSMain.clickStat("热帖榜")}),goWeb(),actControl()});