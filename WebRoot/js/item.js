$(function(){
    $('.content  input[type="submit"]').hover(function(){
        $(this).css({background:'url(../images/search4.png)'})
    },function(){
        $(this).css({background:'url(../images/search3.png)'})
    })
    $('.content #super').hover(function(){
    	$(this).css({background:''})
    },function(){
    	$(this).css({background:''})
    })
	var pre=$('.pre');
	var next=$('.next');
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

	
	$('.show ul ol li').eq(0).css('background', 'url(../images/num-active.png)');

	$('.show').mouseover(function(event) {
		$(this).css('cursor', 'pointer');
	});
	var oLi=$('.show ul ol li');
	var uLi=$('.show ul>li');
	var num_uLi=uLi.length;
	var iNow=0;
	for(var i=0;i<num_uLi;i++){
		oLi[i].index=i;
		oLi.eq(i).mouseover(function(){
			for(var i=0;i<num_uLi;i++){
				oLi[i].style.background='url(../images/num-bg.png)'
				uLi[i].style.display="none";
			}
			oLi[this.index].style.background='url(../images/num-active.png)';
			uLi[this.index].style.display="block";
			iNow=this.index;

		})
		
	}
	pre.click(function(event) {
		
		if(iNow==0){
			return false;
		}else{
			iNow--;
		}
		change(iNow)
	});
	next.click(function(event) {
		if(iNow==num_uLi-1){
			return false;
		}else{
			iNow++;
		}
		
		change(iNow)
		
	});
	function change(iNow){
		for(var i=0;i<num_uLi;i++){
				oLi[i].style.background='url(../images/num-bg.png)'
				uLi[i].style.display="none";
		}
		oLi[iNow].style.background='url(../images/num-active.png)';
		uLi[iNow].style.display="block";
	}

	$('#str').blur(function(){
		var num=$.trim($(this).val()).length;
		if(num>120||num==0){
			$(this).val(' 最多输入60字')
		}
	})

	$('.save').hover(function() {
		$(this).css({
            cursor: 'pointer',
			background: 'url(../images/save1.png)'
		});
	}, function() {
		$(this).css('background', 'url(../images/save.png)');
	});
    $('.save').click(function(){
        $('#saveBtn').attr('value',1);

        $('.save form').submit();
    })
    $('.delete').hover(function(){
        $(this).css({cursor:'pointer',background:'url(../images/save1.png)'})
    },function(){
        $(this).css({background:'url(../images/save.png)'})
    }).click(function(){
            $('#deleteBtn').attr('value',1);
            $('.save form').submit();
        })




    $('#liuyan').hover(function(){
        $(this).css({cursor:'pointer',background:'#3388b3'})
    },function(){
        $(this).css('background',"#38b5f5")
    })


    $('.newBg').css('display','none');
    var that=null;
    $('.exchange').delegate('.repBtn','click',function(){
        that=$(this);
        $('.newBg').css('display','block');

    })
    $('.exchange').delegate('.repBtn','mouseover',function(){
        $(this).css({cursor:'pointer',color:'#3296c9'})
    })
    $('.exchange').delegate('.repBtn','mouseout',function(){
        $(this).css({color:'#4cbaf3'})
    })

    $('#liuyan').bind('click',function(){
            var lyRight=$('.liuyan-right');
            var str=$('.liuyan-left textarea').val();

            if(str.length!=0){

                /*var newExchange=$('<div class="exchange"></div>');
                var newLeave=$('<div class="leave"></div>')
                var newLeaContent=$('<div class="leave-content"></div>');
                var newBuyer=$('<span class="fr innerByer"></span>');
                var newRepBtn=$('<span class="fr repBtn" ></span>');
                newExchange.prepend(newLeave);
                newLeave.append(newLeaContent);
                newLeaContent.text();
                newLeaContent.append(newBuyer);
                newLeaContent.append(newRepBtn);
                newBuyer.text('');
                newRepBtn.text('');
                lyRight.prepend(newExchange);*/
                $('#msg').attr('value',str);
                $('#form5').submit()
                
            }
    })
    $('.newForm input').hover(function(){
        $(this).css({cursor:'pointer',background:'#2a95cc'})
    },function(){
        $(this).css('background','#38b5f5')
    })




    $('.newForm input').bind('click',function(){
        var newStr= $.trim($('.newForm textarea').val());
        if(newStr.length!=0&&newStr.length<100){

            var parExchange=that.parents('.exchange');

            var newLeave=$('<div class="leave"></div>')
            var newLeaContent=$('<div class="leave-content"></div>');
            var newBuyer=$('<span class="fr innerByer"></span>');
            var newRepBtn=$('<span class="fr repBtn" ></span>');

            parExchange.append(newLeave);
            newLeave.append(newLeaContent);
            newLeaContent.text(newStr);
            newLeaContent.append(newBuyer);
            newLeaContent.append(newRepBtn);
            newBuyer.text('李四');
            newRepBtn.text('回复');
            newStr=$('.newForm textarea').val('');
            $('.newBg').css('display','none');$('.newBg').css('display','none');

        }
    })
    $('#lyForm input').click(function(){
        $('#lyForm').submit()
    })




})