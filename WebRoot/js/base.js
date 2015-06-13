$(function(){
	var wClient=$(window).width();
	var hClient=$(window).height();
	$('.used,.buy').hover(function(){
		$(this).css('cursor', 'pointer');
	});
    var str='used';
    $('#hide').attr('value',str);
	$('.used,.buy').click(function(event) {
		
		$('.used,.buy').css('background', 'url(../images/select.png)');
		$(this).css('background', 'url(../images/selected.png)');

		/*$('#hide').attr('value',$(this).attr('class'));*/
        str=$(this).attr('class');
        $('#hide').attr('value',str);
       

		
	});

    $('.search_button').click(function(){
        

    })

	$('.search_button').hover(function(){

		$(this).css('cursor','pointer').attr('src', '../images/search1.png');
	},function(){
		$(this).attr('src', '../images/search.png');
	});


    $('.goods_submit,.buy_submit').click(function(){
        var attr=$(this).attr('data');
        $('#hideButton').attr('value',attr);

    })
    $('.goods_submit,.buy_submit,.search_button').click(function(){
        $('form').eq(0).submit();
    })
    $('.goods_submit,.buy_submit').hover(function(){
        $(this).css({cursor:'pointer',background:'url(../images/button1.png)'})
    },function(){
        $(this).css({background:'url(../images/button.png)'})
    })



	$('.register,.login').css({
		left: (wClient-298)/2,
		top: (hClient-306)/2
	});
	$('.bgNew').css({
		width:wClient,
		height:hClient,
		opacity:0.7
	});


	$('.register_button,.login_button').hover(function() {
		$(this).css({
			cursor: 'pointer'
			
		});
	});

	$('.register_button').click(function(event) {
		$('.bgNew,.register').css('display','block');
	});
	$('.goods_submit,.buy_submit,.login_button').click(function(event) {
		$('.login,.bgNew').css('display', 'block');
	});

	/*......................................................................*/
	/*$('.bgDiv').eq(3).css('height', 66);*/
	$('.box').hover(function() {
		$(this).find('.bgDiv').css({
			display: 'block',
			cursor: 'pointer'
		});
	}, function() {
		$(this).find('.bgDiv').css('display', 'none');
	});


    var bg=$('.bgDiv');
    var numBg=bg.size();

    var links=$('#links');
    var arr=['闲置数码','书籍杂志','家具日用','服鞋配饰','竹苑小市场'];

    for(var i=0;i<numBg;i++){
        bg[i].index=i;
        bg.eq(i).click(function(){
            var msg=arr[this.index];
            links.attr('value',msg);
           $('#formLinks').submit()
        })

    }
    $('.first_session img,.second_session img').load(function(){
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