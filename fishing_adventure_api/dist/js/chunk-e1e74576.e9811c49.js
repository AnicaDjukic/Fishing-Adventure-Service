(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-e1e74576"],{2257:function(t,e,n){},"5d37":function(t,e,n){"use strict";n("2257")},7390:function(t,e,n){},"95e9":function(t,e,n){"use strict";n("7390")},a434:function(t,e,n){"use strict";var o=n("23e7"),c=n("da84"),i=n("23cb"),r=n("5926"),l=n("07fa"),a=n("7b0b"),s=n("65f0"),u=n("8418"),d=n("1dde"),h=d("splice"),b=c.TypeError,f=Math.max,p=Math.min,O=9007199254740991,j="Maximum allowed length exceeded";o({target:"Array",proto:!0,forced:!h},{splice:function(t,e){var n,o,c,d,h,v,w=a(this),g=l(w),m=i(t,g),k=arguments.length;if(0===k?n=o=0:1===k?(n=0,o=g-m):(n=k-2,o=p(f(r(e),0),g-m)),g+n-o>O)throw b(j);for(c=s(w,o),d=0;d<o;d++)h=m+d,h in w&&u(c,d,w[h]);if(c.length=o,n<o){for(d=m;d<g-o;d++)h=d+o,v=d+n,h in w?w[v]=w[h]:delete w[v];for(d=g;d>g-o+n;d--)delete w[d-1]}else if(n>o)for(d=g-o;d>m;d--)h=d+o-1,v=d+n-1,h in w?w[v]=w[h]:delete w[v];for(d=0;d<n;d++)w[d+m]=arguments[d+2];return w.length=g-o+n,c}})},cd49:function(t,e,n){"use strict";n.r(e);var o=n("7a23"),c=function(t){return Object(o["I"])("data-v-7ec95357"),t=t(),Object(o["G"])(),t},i=c((function(){return Object(o["i"])("div",{class:"title"},[Object(o["i"])("h1",null,"Advertiser complaints"),Object(o["i"])("i",{class:"fas fa-exclamation-circle fa-4x",style:{"font-family":"'Font Awesome 5 Pro'"}})],-1)})),r=c((function(){return Object(o["i"])("div",{style:{"background-color":"#212529",padding:"15px","text-align":"-webkit-center"}},[Object(o["i"])("div",{class:"container w-100 row row-cols-1 row-cols-sm-1 row-cols-md-4",style:{"justify-content":"space-evenly","align-items":"center",height:"38px",color:"#dddddd","font-size":"20px"}}," Sanctioning a client will add a penalty to their account. ")],-1)})),l={class:"table-users"},a=c((function(){return Object(o["i"])("thead",null,[Object(o["i"])("tr",null,[Object(o["i"])("th",null,"Service"),Object(o["i"])("th",null," Advertiser "),Object(o["i"])("th",null,"Client "),Object(o["i"])("th",null,"Report "),Object(o["i"])("th",{colspan:"2"},"Sanction?")])],-1)})),s=["report"],u=["onClick"],d=["onClick"];function h(t,e,n,c,h,b){return Object(o["F"])(),Object(o["h"])("div",null,[i,r,Object(o["i"])("div",null,[Object(o["i"])("table",l,[a,Object(o["i"])("tbody",null,[(Object(o["F"])(!0),Object(o["h"])(o["a"],null,Object(o["M"])(t.reports,(function(t){return Object(o["F"])(),Object(o["h"])("tr",{key:t.id,report:t},[Object(o["i"])("td",null,Object(o["R"])(t.serviceName),1),Object(o["i"])("td",null,Object(o["R"])(t.advertiserFullName),1),Object(o["i"])("td",null,Object(o["R"])(t.clientFullName),1),Object(o["i"])("td",null,Object(o["R"])(t.content),1),Object(o["i"])("td",null,[Object(o["i"])("button",{class:"green-btn",onClick:function(e){return b.sanctionClient(t)}}," Yes",8,u)]),Object(o["i"])("td",null,[Object(o["i"])("button",{class:"black-btn",onClick:function(e){return b.dontSanctionClient(t)}}," No",8,d)])],8,s)})),128))])])])])}n("a434");var b=n("bc3a"),f=n.n(b),p={data:function(){return{searchText:"",searchResults:[],reports:[]}},mounted:function(){var t=this;f.a.get("http://localhost:8080/reservationReport/getReportsAwaitingReview",{headers:{"Access-Control-Allow-Origin":"http://localhost:8080",Authorization:"Bearer "+localStorage.refreshToken}}).then((function(e){t.reports=e.data}))},methods:{sanctionClient:function(t){var e=this;f.a.put("http://localhost:8080/reservationReport/sanctionClient/",t,{headers:{"Access-Control-Allow-Origin":"http://localhost:8080",Authorization:"Bearer "+localStorage.refreshToken}}).then((function(){e.reports.splice(e.reports.indexOf(t)),e.$toast.show("User has been sanctioned.",{duration:3e3})}))},dontSanctionClient:function(t){f.a.put("http://localhost:8080/reservationReport/rejectPenalty/",t,{headers:{"Access-Control-Allow-Origin":"http://localhost:8080",Authorization:"Bearer "+localStorage.refreshToken}}).then(this.reports.splice(this.reports.indexOf(t)))}}},O=(n("95e9"),n("5d37"),n("6b0d")),j=n.n(O);const v=j()(p,[["render",h],["__scopeId","data-v-7ec95357"]]);e["default"]=v}}]);
//# sourceMappingURL=chunk-e1e74576.e9811c49.js.map