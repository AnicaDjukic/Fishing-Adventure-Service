(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2964e9ae"],{"23a4":function(e,t,i){},3413:function(e,t,i){"use strict";i("9720")},"3f90":function(e,t,i){"use strict";i.r(t);var a=i("7a23"),n=i("4de3"),r=i.n(n),o=function(e){return Object(a["I"])("data-v-1d13e293"),e=e(),Object(a["G"])(),e},s=o((function(){return Object(a["i"])("div",{class:"title"},[Object(a["i"])("h1",null,"Reservations"),Object(a["i"])("img",{style:{height:"10rem"},src:r.a})],-1)})),c={style:{"background-color":"#212529",padding:"15px"}},l={class:"container w-100",style:{"flex-direction":"column"}},d={class:"row row-cols-1 row-cols-sm-1 row-cols-md-4",style:{"justify-content":"space-evenly","align-items":"center"}},h=o((function(){return Object(a["i"])("div",{class:"col-md-3"},[Object(a["i"])("input",{class:"form-control me-2",type:"search",placeholder:"Search","aria-label":"Search"})],-1)})),u={class:"col-md-4"},b=Object(a["k"])('<div class="col-md-2" data-v-1d13e293><select class="form-select form-select-dark form-select-sm text-white bg-dark" aria-label=".form-select-sm example" data-v-1d13e293><option selected data-v-1d13e293>All statuses</option><option value="1" data-v-1d13e293>Upcoming</option><option value="2" data-v-1d13e293>Current</option><option value="3" data-v-1d13e293>Finished</option><option value="4" data-v-1d13e293>Report filled</option></select></div>',1),p={style:{"margin-top":"5%"}};function v(e,t,i,n,r,o){var v=Object(a["O"])("Datepicker"),O=Object(a["O"])("AdvertiserReservationCard"),m=Object(a["O"])("AdvertiserReservationModal"),y=Object(a["O"])("AdvertiserReportModal");return Object(a["F"])(),Object(a["h"])("div",null,[s,Object(a["i"])("div",c,[Object(a["i"])("div",l,[Object(a["i"])("div",d,[h,Object(a["i"])("div",u,[Object(a["m"])(v,{style:{width:"100%","margin-right":"10px",border:"1px solid white","border-radius":"5px"},dark:"",id:"picker",modelValue:e.date,"onUpdate:modelValue":t[0]||(t[0]=function(t){return e.date=t}),range:"",placeholder:"Select date",enableTimePicker:!1},null,8,["modelValue"])]),b])])]),Object(a["i"])("div",p,[(Object(a["F"])(!0),Object(a["h"])(a["a"],null,Object(a["M"])(e.searchResults,(function(e){return Object(a["F"])(),Object(a["f"])(O,{key:e.id,entity:e,onCreateReservation:o.createReservation,onCreateReport:o.createReport},null,8,["entity","onCreateReservation","onCreateReport"])})),128))]),Object(a["m"])(m,{id:"AdvertiserReservationModal",clientEmail:e.clientEmail,clientName:e.clientName,clientSurname:e.clientSurname},null,8,["clientEmail","clientName","clientSurname"]),Object(a["m"])(y,{id:"AdvertiserReportModal",reportDto:e.reportDto},null,8,["reportDto"])])}i("b0c0");var O=function(e){return Object(a["I"])("data-v-3e297b1a"),e=e(),Object(a["G"])(),e},m={class:"card mb-3 bg-dark mt-3",style:{width:"65%",margin:"auto"}},y={class:"row g-0"},f={class:"col-md-4 shadow-none"},j=["src"],g={class:"col-md-8 shadow-none",name:"main-col"},w={class:"card-body shadow-none"},R={class:"card-text shadow-none",style:{display:"flex"}},S={class:"card-title shadow-none"},D={key:0,class:"reservationStatus shadow-none",style:{"background-color":"#a28800"}},P=O((function(){return Object(a["i"])("b",{class:"shadow-none"},"Upcoming",-1)})),k=[P],A={key:1,class:"reservationStatus shadow-none",style:{"background-color":"#41005f"}},x=O((function(){return Object(a["i"])("b",{class:"shadow-none"},"Finished",-1)})),F=[x],T={key:2,class:"reservationStatus shadow-none",style:{"background-color":"#0c442a"}},C=O((function(){return Object(a["i"])("b",{class:"shadow-none"},"Current",-1)})),M=[C],I={key:3,class:"reservationStatus shadow-none",style:{"background-color":"rgb(33 22 143)"}},B=O((function(){return Object(a["i"])("b",{class:"shadow-none"},"Report filled",-1)})),E=[B],H={class:"card-text shadow-none",style:{display:"flex","align-items":"center"}},N={class:"shadow-none",style:{width:"100%"}},_={class:"row shadow-none"},L=O((function(){return Object(a["i"])("p",{class:"card-text text-left shadow-none col-md-4"}," Client: ",-1)})),z={class:"advertiserTitle shadow-none col-md-6",style:{"margin-left":"11px"}},U={key:0,class:"row shadow-none"},V=O((function(){return Object(a["i"])("p",{class:"card-text text-left shadow-none col-md-4"}," Period: ",-1)})),W={class:"card-text text-left shadow-none col-md-8"},G={key:1,class:"row shadow-none"},$=O((function(){return Object(a["i"])("p",{class:"card-text text-left shadow-none col-md-4"},"Date:",-1)})),q={class:"card-text text-left shadow-none col-md-8"},J={class:"row shadow-none"},Y=O((function(){return Object(a["i"])("p",{class:"card-text text-left shadow-none col-md-4"},"Price:",-1)})),K={class:"card-text text-left shadow-none col-md-8"},Q=O((function(){return Object(a["i"])("i",{class:"fas fa-dollar-sign"},null,-1)})),X={class:"row shadow-none"},Z=O((function(){return Object(a["i"])("p",{class:"card-text text-left shadow-none col-md-4"}," Persons: ",-1)})),ee={class:"card-text text-left shadow-none col-md-8"},te=O((function(){return Object(a["i"])("p",{class:"card-text text-left shadow-none"}," Additional services: ",-1)})),ie={class:"row shadow-none",style:{"margin-left":"1%"}},ae={key:0,class:"additionalServices shadow-none"},ne={class:"manageReservation shadow-none"};function re(e,t,n,r,o,s){return Object(a["F"])(),Object(a["h"])("div",null,[Object(a["i"])("div",m,[Object(a["i"])("div",y,[Object(a["i"])("div",f,[Object(a["i"])("img",{style:{width:"100%",height:"225px","object-fit":"cover"},src:i("1771")("./"+n.entity.imagePath),class:"img-fluid rounded-start shadow-none"},null,8,j)]),Object(a["i"])("div",g,[Object(a["i"])("div",w,[Object(a["i"])("div",R,[Object(a["i"])("h5",S,Object(a["R"])(n.entity.name),1),"Upcoming"==n.entity.status?(Object(a["F"])(),Object(a["h"])("p",D,k)):Object(a["g"])("",!0),"Finished"==n.entity.status?(Object(a["F"])(),Object(a["h"])("p",A,F)):Object(a["g"])("",!0),"Current"==n.entity.status?(Object(a["F"])(),Object(a["h"])("p",T,M)):Object(a["g"])("",!0),"Report filled"==n.entity.status?(Object(a["F"])(),Object(a["h"])("p",I,E)):Object(a["g"])("",!0)]),Object(a["i"])("div",H,[Object(a["i"])("div",N,[Object(a["i"])("div",_,[L,Object(a["i"])("p",z," @"+Object(a["R"])(n.entity.clientName)+Object(a["R"])(n.entity.clientSurname),1)]),"adventure"!=e.entityType?(Object(a["F"])(),Object(a["h"])("div",U,[V,Object(a["i"])("p",W,Object(a["R"])(e.startDate)+" - "+Object(a["R"])(e.endDate),1)])):(Object(a["F"])(),Object(a["h"])("div",G,[$,Object(a["i"])("p",q,Object(a["R"])(e.startDate)+" ("+Object(a["R"])(e.hours)+"h) ",1)])),Object(a["i"])("div",J,[Y,Object(a["i"])("p",K,[Object(a["l"])(Object(a["R"])(n.entity.price)+" ",1),Q])]),Object(a["i"])("div",X,[Z,Object(a["i"])("p",ee,Object(a["R"])(n.entity.persons),1)]),te,Object(a["i"])("div",ie,[0==n.entity.additionalServices.length?(Object(a["F"])(),Object(a["h"])("p",ae," Not included ")):Object(a["g"])("",!0),(Object(a["F"])(!0),Object(a["h"])(a["a"],null,Object(a["M"])(n.entity.additionalServices,(function(e){return Object(a["F"])(),Object(a["h"])("p",{class:"additionalServices shadow-none",key:e.id},Object(a["R"])(e.name),1)})),128))])]),Object(a["i"])("div",ne,["Finished"==n.entity.status?(Object(a["F"])(),Object(a["h"])("button",{key:0,class:"btn btn-primary shadow-none mb-2",style:{"background-color":"#592073","border-color":"#b89fc3",width:"max-content","margin-bottom":"3rem !important"},onClick:t[0]||(t[0]=function(){return s.createReport&&s.createReport.apply(s,arguments)})}," Write report ")):Object(a["g"])("",!0),"Current"==n.entity.status?(Object(a["F"])(),Object(a["h"])("button",{key:1,class:"btn btn-primary shadow-none mb-2",style:{"background-color":"#026756","border-color":"#a0c6c0",width:"max-content","margin-bottom":"3rem !important"},onClick:t[1]||(t[1]=function(){return s.createReservation&&s.createReservation.apply(s,arguments)})}," New reservation ")):Object(a["g"])("",!0)])])])])])])])}var oe=i("c1df"),se=i.n(oe),ce=i("bc3a"),le=i.n(ce),de={props:["entity"],emits:["createReservation","createReport"],data:function(){return{date:"12/20/2021 - 12/25/2021",startDate:"",endDate:"",entityType:"",hours:""}},mounted:function(){var e=this;this.startDate=new Date(this.entity.startDate),this.endDate=new Date(this.entity.endDate),this.startDate=se()(this.startDate).format("MM/DD/yyyy HH:mm"),this.endDate=se()(this.endDate).format("MM/DD/yyyy HH:mm"),le.a.get("/users/getRole",{headers:{"Access-Control-Allow-Origin":"",Authorization:"Bearer "+localStorage.refreshToken}}).then((function(t){var i=t.data;"ROLE_VACATION_HOME_OWNER"==i?e.entityType="cottage":"ROLE_BOAT_OWNER"==i?e.entityType="boat":"ROLE_FISHING_INSTRUCTOR"==i&&(e.entityType="adventure",e.hours=Math.abs(e.entity.startDate-e.entity.endDate)/36e5)}))},methods:{createReservation:function(){this.$emit("createReservation",this.entity.clientEmail,this.entity.clientName,this.entity.clientSurname)},createReport:function(){var e={reservationId:this.entity.reservationId,email:this.entity.clientEmail,name:this.entity.name,clientName:this.entity.clientName,clientSurname:this.entity.clientSurname,startDate:se()(new Date(this.entity.startDate)).format("MM/DD/yyyy HH:mm"),endDate:se()(new Date(this.entity.endDate)).format("MM/DD/yyyy HH:mm")};this.$emit("createReport",e)}}},he=(i("9e46"),i("6b0d")),ue=i.n(he);const be=ue()(de,[["render",re],["__scopeId","data-v-3e297b1a"]]);var pe=be,ve=function(e){return Object(a["I"])("data-v-19b5768d"),e=e(),Object(a["G"])(),e},Oe={class:"modal-dialog modal-dialog-centered"},me={class:"modal-content",style:{height:"40.5rem"}},ye={class:"modal-header"},fe={style:{"font-size":"23px"}},je=ve((function(){return Object(a["i"])("i",{class:"fas fa-times fa-lg"},null,-1)})),ge=[je],we={class:"modal-body"},Re={class:"element"},Se={style:{color:"white"}},De=ve((function(){return Object(a["i"])("option",{value:""},null,-1)})),Pe=["value"],ke={key:0},Ae=ve((function(){return Object(a["i"])("h6",{style:{color:"white",margin:"5% 0"}},"Reservation dates:",-1)})),xe={key:1},Fe=ve((function(){return Object(a["i"])("h6",{style:{color:"white",margin:"5% 0"}},"Reservation date:",-1)})),Te=ve((function(){return Object(a["i"])("h6",{style:{color:"white",margin:"5% 0"}}," Select additional services: ",-1)})),Ce={id:"table-wrapper",style:{"margin-top":"-5%"}},Me={id:"table-scroll"},Ie={class:"table"},Be={key:0,style:{color:"white"}},Ee={style:{color:"white"}},He={style:{color:"white"}},Ne=["id","onClick"],_e={class:"input-group",style:{"margin-top":"1rem"}},Le=ve((function(){return Object(a["i"])("span",{class:"input-group-text",style:{width:"5rem"}},"Persons:",-1)})),ze=["max"],Ue={class:"input-group",style:{"margin-top":"1rem"}},Ve=ve((function(){return Object(a["i"])("span",{class:"input-group-text",style:{width:"5rem"}},"Price:",-1)})),We={key:2,class:"input-group",style:{"margin-top":"1rem"}},Ge=ve((function(){return Object(a["i"])("span",{class:"input-group-text",style:{width:"9rem"}},"Owner presence:",-1)})),$e={class:"form-control",style:{display:"flex","justify-content":"space-around",padding:"0"}},qe={style:{color:"red","margin-top":"1rem"}},Je={class:"modal-footer steps-div"};function Ye(e,t,i,n,r,o){var s=Object(a["O"])("Datepicker");return Object(a["F"])(),Object(a["h"])("div",{class:"modal fade dark",id:"AdvertiserReservationModal","aria-labelledby":"OfferModalLabel","aria-hidden":"true","data-bs-backdrop":"static","data-bs-keyboard":"false",onShow:t[10]||(t[10]=function(){return o.closeModal&&o.closeModal.apply(o,arguments)})},[Object(a["i"])("div",Oe,[Object(a["i"])("div",me,[Object(a["i"])("div",ye,[Object(a["i"])("h3",fe," New reservation for "+Object(a["R"])(i.clientName)+" "+Object(a["R"])(i.clientSurname),1),Object(a["i"])("button",{type:"button",class:"btn-close","data-bs-dismiss":"modal",onClick:t[0]||(t[0]=function(){return o.closeModal&&o.closeModal.apply(o,arguments)})},ge)]),Object(a["i"])("div",we,[Object(a["i"])("div",Re,[Object(a["i"])("h6",Se,"Select "+Object(a["R"])(e.entityType),1),Object(a["i"])("select",{id:"entitySelectAR",onChange:t[1]||(t[1]=function(){return o.changeSelect&&o.changeSelect.apply(o,arguments)})},[De,(Object(a["F"])(!0),Object(a["h"])(a["a"],null,Object(a["M"])(e.selectData,(function(e){return Object(a["F"])(),Object(a["h"])("option",{value:e.label,key:e.code},Object(a["R"])(e.label),9,Pe)})),128))],32)]),"adventure"!=e.entityType?(Object(a["F"])(),Object(a["h"])("span",ke,[Ae,Object(a["m"])(s,{style:{width:"100%","margin-right":"10px",border:"1px solid white","border-radius":"5px"},dark:"",modelValue:e.dateRange,"onUpdate:modelValue":t[2]||(t[2]=function(t){return e.dateRange=t}),onClosed:o.dateRangeChanged,placeholder:"Select dates..",range:"",enableTimePicker:!0},null,8,["modelValue","onClosed"])])):Object(a["g"])("",!0),"adventure"==e.entityType?(Object(a["F"])(),Object(a["h"])("span",xe,[Fe,Object(a["m"])(s,{style:{width:"100%","margin-right":"10px",border:"1px solid white","border-radius":"5px"},dark:"",modelValue:e.adventureReservationDate,"onUpdate:modelValue":t[3]||(t[3]=function(t){return e.adventureReservationDate=t}),onClosed:o.dateRangeChanged,placeholder:"Select date..",enableTimePicker:!0},null,8,["modelValue","onClosed"])])):Object(a["g"])("",!0),Te,Object(a["i"])("div",Ce,[Object(a["i"])("div",Me,[Object(a["i"])("table",Ie,[Object(a["i"])("tbody",null,[void 0!=e.additionalServices&&e.additionalServices.length?Object(a["g"])("",!0):(Object(a["F"])(),Object(a["h"])("tr",Be," No additional services available ")),(Object(a["F"])(!0),Object(a["h"])(a["a"],null,Object(a["M"])(e.additionalServices,(function(e){return Object(a["F"])(),Object(a["h"])("tr",{key:e.id},[Object(a["i"])("td",null,[Object(a["i"])("label",Ee,Object(a["R"])(e.name),1)]),Object(a["i"])("td",null,[Object(a["i"])("label",He,Object(a["R"])(e.price)+" $",1)]),Object(a["i"])("td",null,[Object(a["i"])("input",{id:e.id,type:"checkbox",onClick:function(t){return o.check(e)}},null,8,Ne)])])})),128))])])])]),Object(a["i"])("div",_e,[Le,Object(a["ab"])(Object(a["i"])("input",{type:"number","onUpdate:modelValue":t[4]||(t[4]=function(t){return e.persons=t}),max:e.maxPersons,min:"1",class:"form-control"},null,8,ze),[[a["W"],e.persons]])]),Object(a["i"])("div",Ue,[Ve,Object(a["ab"])(Object(a["i"])("input",{disabled:"",type:"number","onUpdate:modelValue":t[5]||(t[5]=function(t){return e.priceField=t}),min:"0",class:"form-control",onChange:t[6]||(t[6]=function(){return e.recalculatePrice&&e.recalculatePrice.apply(e,arguments)})},null,544),[[a["W"],e.priceField]])]),"boat"==e.entityType?(Object(a["F"])(),Object(a["h"])("div",We,[Ge,Object(a["i"])("div",$e,[Object(a["i"])("div",{class:"ownerPresence",onClick:t[7]||(t[7]=function(){return o.changeOwnerPresence&&o.changeOwnerPresence.apply(o,arguments)}),id:"yesOwnerPresence"}," YES "),Object(a["i"])("div",{class:"ownerPresence",onClick:t[8]||(t[8]=function(){return o.changeOwnerPresence&&o.changeOwnerPresence.apply(o,arguments)}),id:"noOwnerPresence"}," NO ")])])):Object(a["g"])("",!0),Object(a["i"])("h6",qe,[Object(a["i"])("b",null,Object(a["R"])(e.error),1)])]),Object(a["i"])("div",Je,[Object(a["i"])("button",{type:"button",class:"btn btn-outline-primary",onClick:t[9]||(t[9]=function(){return o.createReservation&&o.createReservation.apply(o,arguments)})}," Create ")])])])],32)}i("caad"),i("2532"),i("a434");var Ke={components:{},props:["clientEmail","clientName","clientSurname"],name:"OfferModal",data:function(){return{serviceProfileId:"",persons:1,maxPersons:"",priceField:"",pricePerTimeFrame:"",selectData:"",entityType:"",additionalServices:[],dateRange:[],chosenServices:[],error:"",originalTotalPrice:"",adventureReservationDate:void 0,priceForPeriod:0,priceForServices:0,adventureDurationInMins:[],available:!1,ownerPresence:!1}},mounted:function(){var e=this;le.a.get("/users/getRole",{headers:{"Access-Control-Allow-Origin":"",Authorization:"Bearer "+localStorage.refreshToken}}).then((function(t){var i=t.data;"ROLE_VACATION_HOME_OWNER"==i?(e.entityType="cottage",e.getServiceProfileByHomeOwner()):"ROLE_BOAT_OWNER"==i?(e.entityType="boat",e.getServiceProfileByBoatOwner()):"ROLE_FISHING_INSTRUCTOR"==i&&(e.entityType="adventure",e.getServiceProfileByFishingInstructor())}))},methods:{getAdditionalServicesByName:function(e){var t=this;le.a.get("/serviceProfile/getAdditionalServicesByName/"+e,{headers:{"Access-Control-Allow-Origin":"",Authorization:"Bearer "+localStorage.refreshToken}}).then((function(e){t.additionalServices=e.data}))},changeSelect:function(){var e=this,t=document.getElementById("entitySelectAR").value;this.priceForPeriod=0,this.priceForServices=0,this.priceField="",this.originalTotalPrice=0,""!=t?(this.getAdditionalServicesByName(t),le.a.get("/serviceProfile/getServiceInfoForOfferByName/"+t,{headers:{"Access-Control-Allow-Origin":"",Authorization:"Bearer "+localStorage.refreshToken}}).then((function(t){e.pricePerTimeFrame=t.data.price,e.originalPricePerDay=t.data.price,e.maxPersons=t.data.persons,e.serviceProfileId=t.data.serviceProfileId,e.persons>e.maxPersons&&(e.persons=e.maxPersons),"adventure"==e.entityType&&le.a.get("/fishingAdventure/getDurationById/"+e.serviceProfileId,{headers:{"Access-Control-Allow-Origin":"",Authorization:"Bearer "+localStorage.refreshToken}}).then((function(t){e.adventureDurationInMins=t.data}))}))):(this.serviceProfileId="",this.persons=1,this.maxPersons=1,this.priceField="",this.pricePerTimeFrame="",this.additionalServices=[],this.dateRange=[],this.chosenServices=[],this.error="",this.adventureReservationDate=void 0,this.priceForPeriod=0,this.priceForServices=0,this.originalTotalPrice=0,this.available=!1),this.dateRangeChanged()},dateRangeChanged:function(){var e=this;if(void 0!=this.dateRange[0]&&void 0!=this.dateRange[1]){var t=this.dateRange[1]-this.dateRange[0],i=t/864e5;i=parseInt(i,10);var a=t/36e5-24*i;a=parseInt(a,10),a>12&&(i+=1),this.priceForPeriod=i*this.originalPricePerDay,this.originalTotalPrice=this.priceForPeriod+this.priceForServices,this.priceField=this.originalTotalPrice,"cottage"==this.entityType?le.a.get("/vacationHome/available/dateRange?id="+this.serviceProfileId+"&start="+se()(this.dateRange[0]).format("yyyy-MM-DD HH:mm:ss.SSS")+"&end="+se()(this.dateRange[1]).format("yyyy-MM-DD HH:mm:ss.SSS"),{headers:{"Access-Control-Allow-Origin":"",Authorization:"Bearer "+localStorage.refreshToken}}).then((function(t){t.data?(e.available=!0,e.error=""):(e.error="Chosen date is not available.",e.available=!1)})):le.a.get("/boat/available/dateRange?id="+this.serviceProfileId+"&start="+se()(this.dateRange[0]).format("yyyy-MM-DD HH:mm:ss.SSS")+"&end="+se()(this.dateRange[1]).format("yyyy-MM-DD HH:mm:ss.SSS"),{headers:{"Access-Control-Allow-Origin":"",Authorization:"Bearer "+localStorage.refreshToken}}).then((function(t){t.data?e.ownerPresence?e.checkBoatOwnerAvailability():(e.available=!0,e.error=""):(e.error="Chosen date is not available.",e.available=!1)}))}else if(void 0!=this.adventureReservationDate){var n=this.adventureReservationDate,r=new Date(n.getTime()+6e4*this.adventureDurationInMins);le.a.get("/fishingAdventure/isInstructorAvailable?start="+se()(n).format("yyyy-MM-DD HH:mm:ss.SSS")+"&end="+se()(r).format("yyyy-MM-DD HH:mm:ss.SSS"),{headers:{"Access-Control-Allow-Origin":"",Authorization:"Bearer "+localStorage.refreshToken}}).then((function(t){t.data?(e.available=!0,e.error=""):(e.error="Chosen date is not available.",e.available=!1)})),this.priceForPeriod=this.pricePerTimeFrame,this.originalTotalPrice=this.priceForPeriod+this.priceForServices,this.priceField=this.originalTotalPrice}},changeOwnerPresence:function(e){var t=e.target.innerHTML;t.includes("YES")?(document.getElementById("noOwnerPresence").classList.remove("active"),document.getElementById("yesOwnerPresence").classList.add("active"),this.ownerPresence=!0,""!=this.serviceProfileId&&this.checkBoatOwnerAvailability()):(document.getElementById("yesOwnerPresence").classList.remove("active"),document.getElementById("noOwnerPresence").classList.add("active"),this.ownerPresence=!1),this.dateRangeChanged()},check:function(e){var t=document.getElementById(e.id);if(!0===t.checked)this.chosenServices.push(e),this.priceForServices+=e.price;else{var i=this.chosenServices.indexOf(e);this.priceForServices-=e.price,i>-1&&this.chosenServices.splice(i,1)}this.originalTotalPrice=this.priceForPeriod+this.priceForServices,this.priceField=this.originalTotalPrice},createReservation:function(){if(this.available)if(this.maxPersons<this.persons)this.error="Maximum number of people is "+this.maxPersons+".";else{var e=[],t=[],i=[];"adventure"==this.entityType?(e=this.adventureReservationDate,i=!0,t=new Date(e.getTime()+6e4*this.adventureDurationInMins)):(e=this.dateRange[0],t=this.dateRange[1],i="cottage"==this.entityType||this.ownerPresence);var a={serviceId:this.serviceProfileId,startDate:e,endDate:t,persons:this.persons,chosenServices:this.chosenServices,price:this.priceField,ownersPresence:i,clientEmail:this.clientEmail};le.a.post("/reservation/newByAdvertiser",a,{headers:{"Access-Control-Allow-Origin":"",Authorization:"Bearer "+localStorage.refreshToken}}).then(window.location.reload())}else"boat"!=this.entityType&&(this.error="Chosen date is not available.")},closeModal:function(){this.persons=1,this.maxPersons=1,this.priceField="",this.pricePerTimeFrame="",this.entityType="",this.additionalServices=[],this.dateRange=[],this.chosenServices=[],this.error="",this.adventureReservationDate=void 0,this.priceForPeriod=0,this.priceForServices=0,this.originalTotalPrice=0,this.available=!1,document.getElementById("entitySelectAR").value=""},getServiceProfileByHomeOwner:function(){var e=this;le.a.get("/vacationHome/getNamesByUser",{headers:{"Access-Control-Allow-Origin":"",Authorization:"Bearer "+localStorage.refreshToken}}).then((function(t){e.selectData=t.data}))},getServiceProfileByBoatOwner:function(){var e=this;le.a.get("/boat/getNamesByUser",{headers:{"Access-Control-Allow-Origin":"",Authorization:"Bearer "+localStorage.refreshToken}}).then((function(t){document.getElementById("noOwnerPresence").classList.add("active"),e.selectData=t.data}))},getServiceProfileByFishingInstructor:function(){var e=this;le.a.get("/fishingAdventure/getNamesByUser",{headers:{"Access-Control-Allow-Origin":"",Authorization:"Bearer "+localStorage.refreshToken}}).then((function(t){e.selectData=t.data}))},checkBoatOwnerAvailability:function(){var e=this;le.a.get("/boatOwner/available/dateRange?id="+this.serviceProfileId+"&start="+se()(this.dateRange[0]).format("yyyy-MM-DD HH:mm:ss.SSS")+"&end="+se()(this.dateRange[1]).format("yyyy-MM-DD HH:mm:ss.SSS"),{headers:{"Access-Control-Allow-Origin":"",Authorization:"Bearer "+localStorage.refreshToken}}).then((function(t){t.data?(e.available=!0,e.error=""):(e.error="Boat owner is not available.",e.available=!1)}))}}};i("3413");const Qe=ue()(Ke,[["render",Ye],["__scopeId","data-v-19b5768d"]]);var Xe=Qe,Ze=function(e){return Object(a["I"])("data-v-4b19565e"),e=e(),Object(a["G"])(),e},et={class:"modal-dialog modal-dialog-centered"},tt={class:"modal-content",style:{height:"37rem"}},it={class:"modal-header"},at=Ze((function(){return Object(a["i"])("h3",{style:{"font-size":"23px"}},"Reservation report",-1)})),nt=Ze((function(){return Object(a["i"])("i",{class:"fas fa-times fa-lg"},null,-1)})),rt=[nt],ot={class:"modal-body"},st={style:{display:"flex","justify-content":"space-evenly","margin-top":"1rem"}},ct=Ze((function(){return Object(a["i"])("div",{class:"infoDiv"},[Object(a["i"])("h6",null,"Reservation name:"),Object(a["i"])("div",null,[Object(a["i"])("h6",{style:{"text-align":"left"}},"From:"),Object(a["i"])("h6",{style:{"text-align":"left"}},"To:")]),Object(a["i"])("h6",null,"Client:")],-1)})),lt={class:"infoDiv"},dt={class:"clientDiv"},ht={key:0,class:"optionsDiv"},ut={key:1,class:"textareaDiv"},bt={class:"selectedOption"},pt=Ze((function(){return Object(a["i"])("i",{class:"fas fa-times fa-lg"},null,-1)})),vt=[pt],Ot={style:{color:"red","margin-top":"1rem"}},mt={class:"modal-footer steps-div"};function yt(e,t,i,n,r,o){return Object(a["F"])(),Object(a["h"])("div",{class:"modal fade dark",id:"AdvertiserReportModal","aria-labelledby":"OfferModalLabel","aria-hidden":"true","data-bs-backdrop":"static","data-bs-keyboard":"false",onShow:t[7]||(t[7]=function(){return o.closeModal&&o.closeModal.apply(o,arguments)})},[Object(a["i"])("div",et,[Object(a["i"])("div",tt,[Object(a["i"])("div",it,[at,Object(a["i"])("button",{type:"button",class:"btn-close","data-bs-dismiss":"modal",onClick:t[0]||(t[0]=function(){return o.closeModal&&o.closeModal.apply(o,arguments)})},rt)]),Object(a["i"])("div",ot,[Object(a["i"])("div",st,[ct,Object(a["i"])("div",lt,[Object(a["i"])("h6",null,Object(a["R"])(i.reportDto.name),1),Object(a["i"])("div",null,[Object(a["i"])("h6",null,Object(a["R"])(i.reportDto.startDate),1),Object(a["i"])("h6",null,Object(a["R"])(i.reportDto.endDate),1)]),Object(a["i"])("h6",dt,Object(a["R"])(i.reportDto.clientName)+Object(a["R"])(i.reportDto.clientSurname),1)])]),void 0==e.selectedOption?(Object(a["F"])(),Object(a["h"])("div",ht,[Object(a["i"])("div",{class:"reportOptions",onClick:t[1]||(t[1]=function(){return o.selectOption&&o.selectOption.apply(o,arguments)}),id:"pleasant"}," Pleasant appointment "),Object(a["i"])("div",{class:"reportOptions",onClick:t[2]||(t[2]=function(){return o.selectOption&&o.selectOption.apply(o,arguments)}),id:"sanction"}," Request sanction "),Object(a["i"])("div",{class:"reportOptions",onClick:t[3]||(t[3]=function(){return o.selectOption&&o.selectOption.apply(o,arguments)}),id:"showUp"}," Client didn't show up ")])):Object(a["g"])("",!0),void 0!=e.selectedOption?(Object(a["F"])(),Object(a["h"])("div",ut,[Object(a["i"])("div",bt,[Object(a["l"])(Object(a["R"])(e.selectedOption)+" ",1),Object(a["i"])("button",{type:"button",class:"btn-close",onClick:t[4]||(t[4]=function(){return o.deselectOption&&o.deselectOption.apply(o,arguments)})},vt)]),Object(a["ab"])(Object(a["i"])("textarea",{placeholder:"Write your report..","onUpdate:modelValue":t[5]||(t[5]=function(t){return e.content=t})},null,512),[[a["W"],e.content]])])):Object(a["g"])("",!0),Object(a["i"])("h6",Ot,[Object(a["i"])("b",null,Object(a["R"])(e.error),1)])]),Object(a["i"])("div",mt,[Object(a["i"])("button",{type:"button",class:"btn btn-outline-primary",onClick:t[6]||(t[6]=function(){return o.createReport&&o.createReport.apply(o,arguments)})}," Create ")])])])],32)}var ft={components:{},emits:["reportEntered"],props:["reportDto"],name:"ReportModal",data:function(){return{content:"",error:"",selectedOption:void 0}},mounted:function(){},methods:{selectOption:function(e){this.error="";var t=e.currentTarget.id;document.getElementById("pleasant").classList.remove("active"),document.getElementById("showUp").classList.remove("active"),document.getElementById("sanction").classList.remove("active"),document.getElementById(t).classList.add("active"),this.selectedOption=e.target.innerHTML},deselectOption:function(){this.selectedOption=void 0},closeModal:function(){this.content="",this.error="",this.selectedOption=void 0},createReport:function(){var e=this;if(void 0==this.selectedOption)this.error="Please select one of the options offerd.";else{this.error="";var t="";this.selectedOption.toLowerCase().includes("show")?t="didntShowUp":this.selectedOption.toLowerCase().includes("sanction")&&(t="sanctionRequest");var i={reservationId:this.reportDto.reservationId,content:this.content,option:t};le.a.post("/reservationReport",i,{headers:{"Access-Control-Allow-Origin":"",Authorization:"Bearer "+localStorage.refreshToken}}).then((function(){window.location.reload(),e.$toast.show("Thank you for your entry!")}))}}}};i("ddd0"),i("d4a2");const jt=ue()(ft,[["render",yt],["__scopeId","data-v-4b19565e"]]);var gt=jt,wt={components:{AdvertiserReservationCard:pe,AdvertiserReservationModal:Xe,AdvertiserReportModal:gt},data:function(){return{numberOfPersons:"",range:{start:new Date(2020,9,12),end:new Date(2020,9,16)},date:"",searchText:"",searchResults:[],entities:[],clientEmail:"",clientName:"",clientSurname:"",reportDto:""}},mounted:function(){var e=this;le.a.get("/reservation/allByAdvertiser",{headers:{"Access-Control-Allow-Origin":"",Authorization:"Bearer "+localStorage.refreshToken}}).then((function(t){e.searchResults=t.data,e.entities=t.data}))},methods:{createReservation:function(e,t,i){this.clientEmail=e,this.clientName=t,this.clientSurname=i;var a=document.getElementById("AdvertiserReservationModal"),n=window.bootstrap.Modal.getOrCreateInstance(a);n.show()},createReport:function(e){this.reportDto=e;var t=document.getElementById("AdvertiserReportModal"),i=window.bootstrap.Modal.getOrCreateInstance(t);i.show()}}};i("8750");const Rt=ue()(wt,[["render",v],["__scopeId","data-v-1d13e293"]]);t["default"]=Rt},"6d75":function(e,t,i){},"75ca":function(e,t,i){},8750:function(e,t,i){"use strict";i("23a4")},9720:function(e,t,i){},"9e46":function(e,t,i){"use strict";i("6d75")},a434:function(e,t,i){"use strict";var a=i("23e7"),n=i("da84"),r=i("23cb"),o=i("5926"),s=i("07fa"),c=i("7b0b"),l=i("65f0"),d=i("8418"),h=i("1dde"),u=h("splice"),b=n.TypeError,p=Math.max,v=Math.min,O=9007199254740991,m="Maximum allowed length exceeded";a({target:"Array",proto:!0,forced:!u},{splice:function(e,t){var i,a,n,h,u,y,f=c(this),j=s(f),g=r(e,j),w=arguments.length;if(0===w?i=a=0:1===w?(i=0,a=j-g):(i=w-2,a=v(p(o(t),0),j-g)),j+i-a>O)throw b(m);for(n=l(f,a),h=0;h<a;h++)u=g+h,u in f&&d(n,h,f[u]);if(n.length=a,i<a){for(h=g;h<j-a;h++)u=h+a,y=h+i,u in f?f[y]=f[u]:delete f[y];for(h=j;h>j-a+i;h--)delete f[h-1]}else if(i>a)for(h=j-a;h>g;h--)u=h+a-1,y=h+i-1,u in f?f[y]=f[u]:delete f[y];for(h=0;h<i;h++)f[h+g]=arguments[h+2];return f.length=j-a+i,n}})},d4a2:function(e,t,i){"use strict";i("f878")},ddd0:function(e,t,i){"use strict";i("75ca")},f878:function(e,t,i){}}]);
//# sourceMappingURL=chunk-2964e9ae.290a0077.js.map