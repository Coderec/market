$(function(){
/*................................图片切换................................................*/

	var aLiUl=$('.show li');
	var aLiOl=$('.number li');
	var numb=$('.number');
	var size=aLiUl.size();
    var iNow=0;
    var iWidth=aLiUl.eq(0).width();
    var timer=null;
    var time1=400;
    var time2=3000;
    preWork(aLiUl,size,iWidth);

    function preWork(obj,size,ileft){
        for(var i=1;i<size;i++){
            obj.eq(i).css({left:ileft});
        }
    }
    for(var i=0;i<size;i++){
        aLiOl[i].index=i;
        aLiOl[i].onmouseover=function(){
            $(this).css('cursor','pointer');
            aLiOl.each(function() {
                $(this).attr('class','')
            });
            $(this).attr('class','active');
            if(this.index > iNow){
                aLiUl.eq(this.index).css({left:iWidth});
                aLiUl.eq(iNow).stop().animate({left:-iWidth},time1);
            }
            else if(this.index < iNow){
                aLiUl.eq(this.index).css({left:-iWidth});
                aLiUl.eq(iNow).stop().animate({left:iWidth},time1);
            }
            aLiUl.eq(this.index).stop().animate({left:0},time1);
            iNow = this.index;
        }

    }
    timer=setInterval(change,time2);
    function change(){
        if(iNow==size-1){
            iNow=0
        }else{
            iNow++
        } 
        for(var i=0;i<size;i++){
            aLiOl.eq(i).attr('class', '');
        }
        aLiOl.eq(iNow).attr('class', 'active');
        aLiUl.eq(iNow).css({left:iWidth});
        aLiUl.eq(iNow-1).stop().animate({left:-iWidth},time1,function(){
            aLiUl.eq(iNow-1).css({left:iWidth})
        });
        aLiUl.eq(iNow).stop().animate({left:0},time1);

    }
    $('.show li,.number li').hover(function() {

        clearInterval(timer)
    }, function() {
        timer=setInterval(change,time2);
    });
    







	

	/*...........................................................................*/

	$('.first_session span ').hover(function() {
		$(this).css({
			cursor: 'pointer',
			color: '#2db3f8'
		});
	}, function() {
		$(this).css('color', '#333')
	});

    $('.first_session span').click(function(event) {

        var data=$(this).attr('data');
        $('#change').attr('value',data);

        $('.first_session span').each(function(index, el) {
            $(this).removeClass('title1')
        });
        $(this).addClass('title1');
        if (data=='love') {
            $('.first_session ul:first').addClass('hidden');
            $('.first_session ul:last').removeClass('hidden')
        }else{
            $('.first_session ul:last').addClass('hidden')
            $('.first_session ul:first').removeClass('hidden')
        }
    });


	
})