$(function(){


	var nav=$('.home-nav');
	var div=$('.home-nav>div');
	var box=$('.home-content .box');
	var step=box.find('li').width()+60;

	var pre=$('.pre');
	var next=$('.next');
    var num;
	pre.hover(function() {
		$(this).css('background', 'url("../images/pre1.png")').css('cursor', 'pointer');
	}, function() {
		$(this).css('background', 'url("../images/pre.png")')
	});
	next.hover(function(){
		$(this).css('background', 'url("../images/next1.png")').css('cursor', 'pointer');

	},function(){
		$(this).css('background', 'url("../images/next.png")')
	})
    div.eq(0).addClass('active');
    for(var i=0;i<div.length;i++){
        div[i].index=i;
        div[i].onmouseover=function(){
            $(this).css({cursor:'pointer'})
        }

        div.eq(i).click(function(event) {
            for(var j=0;j<div.length;j++){
                div.eq(j).removeClass('active');
            }

            $(this).addClass('active');
            event.stopPropagation();
            div.unbind('hover');
            data=$(this).attr('data');
            box.each(function(index, el) {
                $(this).addClass('hidden')

            });
            box.eq(this.index).removeClass('hidden')
            num=box.eq(this.index).find('li').size();

        });
    }
    $(this).addClass('active').css('cursor', 'pointer');
    function wUl(obj){
		var num=obj.find('li').length;
		return num*step;
	}
	var number=1;

	pre.click(function(event) {
		if(number==num-1){

			box.not($('.hidden')).find('ul').css('left', -step*number);
			return false;
		}
		box.not($('.hidden')).find('ul').css('left', -step*number);
		number++
	});
	next.click(function(event) {
		if(number==0){
			box.not($('.hidden')).find('ul').css('left', -step*number);
			return false
		}
		box.not($('.hidden')).find('ul').css('left', -step*number);
		number--;
	});















    $('.newBg').css('display','none');
    var leave=$('.leave');
    var partentLi;
    $('.repBtn').click(function(){
        partentLi=$(this).parents('li');
        $('.newForm textarea').val(' ');
        $('.newBg').css('display','block');

    })
    $('.newForm input').click(function(){
        var str=$('.newForm textarea').val();
        if(!$.trim(str).length==0){
            var newRep=$('<div class="reply"></div>');
            $('.newBg').css('display','none');
            newRep.text(str);
            partentLi.find('.replyCon').prepend(newRep);

        }
    })
    $('.newForm input').hover(function(){
        $(this).css({cursor:'pointer',background:'blue'})
    },function(){
        $(this).css('background','#38b5f5')
    })
    $('.repBtn').hover(function(){
        $(this).css({cursor:'pointer',color:'#3296c9'})
    },function(){
        $(this).css({color:'#3ab6f6'})
    })

     $('.box img').load(function(){
        var bi=$(this).width()/$(this).height();
        if(bi>1){
            $(this).width(180);
            $(this).height($(this).width()*(1/bi));
        }else{
            $(this).height(180);
            $(this).width($(this).height()*bi);
        }
    })

})