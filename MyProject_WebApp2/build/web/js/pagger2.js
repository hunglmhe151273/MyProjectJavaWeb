/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function createPaggerr(div,pageindex,gap,totalpage,curcateid,cursubid)
{
        var container = document.getElementById(div);
        //container.innerHTML = pageindex+' '+gap +' '+totalpage;
        
        if(pageindex - gap>1){
            container.innerHTML += '<a href="admin?cateId='+curcateid+'&subId='+cursubid+'&page=1">First</a>';
        }
        for(var i = pageindex - gap;i< pageindex; i++){
            if(i>0)
            container.innerHTML += '<a href="admin?cateId='+curcateid+'&subId='+cursubid+'&page='+i+'">'+i+'</a>';
        }
        container.innerHTML += '<a class="text-success" class="P_active" href="Shop?cateId='+curcateid+'&subId='+cursubid+'&page='+pageindex+'">'+pageindex+'</a>';
        for(var i = pageindex + 1;i<= pageindex+gap; i++){
            if(i<= totalpage)
            container.innerHTML += '<a href="admin?cateId='+curcateid+'&subId='+cursubid+'&page='+i+'">'+i+'</a>';
        }
        if(pageindex + gap < totalpage){
            container.innerHTML += '<a href="admin?cateId='+curcateid+'&subId='+cursubid+'&page='+totalpage+'">Last</a>';
        }
}
