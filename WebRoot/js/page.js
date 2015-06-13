/**
 * Created by colin on 14-4-21.
 */
$(function(){
    var uLi=$(' .res-list ul li');
    var oLi=$('.res-list ol li');
    var allOli=oLi.size();
    var allNum=uLi.size();
    var singleNum=5;
    var start=0;
    oLi.eq(0).css('background','red');
    $('.next').click(function(){
        start++;

    })
    show(uLi,allNum,0,5);
    oLi.click(function(e){
        e.preventDefault();
        for(var i=0;i<allOli;i++){
            oLi.eq(i).css('background','#3cb8f8')
        }
        $(this).css('background','red');
        start=$(this).find('a').text()-1;
    })
})
function show(obj,allNum,start,singleNum){
    for(var i=0;i<allNum;i++){
        obj[i].index=i;
        obj[i].style.display='none'
    }
    for(var i=start;i<start+singleNum;i++){
        obj[i].style.display='block';
    }
}