$(function(){
	$('.side-left span').css({
		color: 'red',
		visibility: 'hidden'
	});
   

    /*........................................标题验证............................................................*/
	$('#title').blur(function(event) {
		var str=$(this).val();
		if (getByteLen(str)>16||getByteLen(str)<6) {
			$(this).next('span').css('visibility', 'visible');

		}else{
			$(this).next('span').css('visibility', 'hidden');
        }

	});
	$('#item,#usetime').mouseover(function(event) {
		$(this).css('cursor', 'pointer');
	});



/*.......................................类别选择.........................................................*/

    var btn=1;
    $('#usetime').click(function(event){
        var that=$(this)
        event.stopPropagation();
        var arr=['全新','九成新','八成新','七成新','六成新'];
        showHidden(event,arr,'usetime')

    });
    $('#item').click(function(event){
        var that=$(this)
        event.stopPropagation();
        var arr=['闲置数码','书籍杂志','家具日用','服鞋配饰','竹苑小市场'];
        showHidden(event,arr,'item')

    });
    $('body').click(function(){
        $('.newClass').css('display','none');
    })
    $('#description').blur(function(){
        var str=$.trim($(this).val());
        if(getByteLen(str)==0||getByteLen(str)>200){
            $(this).val('最多输入200字');
        }
    })


    function showHidden(event,arr,id){
        var x=event.clientX;
        var y=event.clientY;

        if(btn){
            var ul=$('<ul class="newClass"></ul>');
            for(var i=0;i<arr.length;i++){
                var li=$("<li>"+ arr[i] +"</li>");
                ul.append(li)
            };
            $('body').append(ul);
            btn=0;
        }else{

            $('.newClass').css('display','none');
            btn=1
        }
        $('.newClass li').css({
            width: 118,
            height: '28px',
            background:'#e2e2e2',
            marginBottom:1,
            lineHeight:'28px',
            textAlign:'center',
            zIndex:123
        });
        $('.newClass ').css({
            position:'absolute',
            left:x+30,
            top:y-30
        });

        $('.newClass li').each(function(index, el) {
            $(this).hover(function() {
                $(this).css({
                    cursor: 'pointer',
                    background: '#a9a6a6'
                });
            }, function() {
                $(this).css('background', '#e2e2e2');
            });
            $(this).click(function() {
                if(btn==1){
                    iNow++;
                }
                btn=0;

                var val=$(this).text();
                $('#'+id).css('background','url(../images/post-check1.png) ').val(val);
                $('.'+id).attr('value',val);

                $('.newClass').css('display', 'none');

            });
        });
    }

	/*.................................地区选择.........................................*/
	
	$('.nanhu,.wenchang').hover(function(){
		$(this).css('cursor', 'pointer');
	});
	$('.nanhu,.wenchang').click(function(){
		var cla=$(this).attr( 'class');
        $('#hidePlace').attr('value',cla);
		$('.nanhu,.wenchang').css('background', 'url(../images/post-select.png)');
		$(this).css('background', 'url(../images/post-selected.png)');
	});

/*........................................电话号码验证.....................................................................*/

    $('#phone').blur(function(){
        var numb= $.trim($(this).val());
        if(!isMobil(numb)){
            $(this).next('span').css('visibility','visible');

        }else{
            $(this).next('span').css('visibility','hidden')
        }
            
    })

/*...................................价格验证...............................................................*/
    $('#original-price,#price,#phone').keyup(function(){
        this.value = this.value.replace(/[^\d.]/g,"");

    })

    $("#original-price ,#price").blur(function(){
        var original=parseInt($('#original-price').val());
        var price=parseInt($('#price').val());
        var numb=$(this).val();
        if(!isPrice(numb)){
            $(this).next('span').css('visibility','visible');

        }else{
            $(this).next('span').css('visibility','hidden');
            if(price>original){
                $('#price').next('span').text('现价不能高于原价不匹配').css('visibility', 'visible');

            }else{
                $('#price').next('span').css('visibility', 'hidden');
                
            }

        }

    });


    $('#submit').mouseover(function(){
        $(this).css('cursor','pointer')
    })
    var btn1=1;
    $('.iptFile').change(function(){

        var i;
        i=checkImgType(this);
        if(i==1&&btn1){
            
            btn1=0;
        }


    })

    function checkImgType(ths){
        if (ths.value == "") {
            alert("请上传图片");
        } else {
            if (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(ths.value)) {
                alert("图片类型必须是.gif,jpeg,jpg,png中的一种");
                ths.value = "";
                i=0
            }else{
                i=1;
            }
        }
        return i


    }


    
    $('#submit').click(function(){
        var arr=[];
        var str=$('#title').val();
        if(5<getByteLen(str)&&getByteLen(str)<17){
            if(arr.indexOf('title')==-1){
                arr.push('title');
            }

        }else{
            var index=arr.indexOf('title');
            if(arr.indexOf('title')!=-1){
                arr.splice(index,1);
            }

        }
        
        if( $('.item').attr('value')!=' '){
            if(arr.indexOf('item')==-1){
                arr.push('item');
            }

        }else{
            var index=arr.indexOf('title');
            if(arr.indexOf('item')!=-1){
                arr.splice(index,1);
            }
        }
        if( $('.usetime').attr('value')!=' '){
            if(arr.indexOf('usetime')==-1){
                arr.push('usetime');
            }

        }else{
            var index=arr.indexOf('usetime');
            if(arr.indexOf('usetime')!=-1){
                arr.splice(index,1);
            }
        }
        var str1=$.trim($('#description').val())
        if(0<getByteLen(str1)&&getByteLen(str1)<200){
            if(arr.indexOf('description')==-1){
                arr.push('description');
            }
        }else{
            var index=arr.indexOf('description');
            if(arr.indexOf('description')!=-1){
                arr.splice(index,1);
            }
        }
        if(isMobil($.trim($('#phone').val()))){
            if(arr.indexOf('phone')==-1){
                arr.push('phone');
            }
        }else{
            var index=arr.indexOf('phone');
            if(arr.indexOf('phone')!=-1){
                arr.splice(index,1);
            }
        }
        var original=parseInt($('#original-price').val());
        var price=parseInt($('#price').val());
        var numb=$(this).val()
        if(isPrice(original)&&isPrice(price)&&original>=price){
            if(arr.indexOf('price')==-1){
                arr.push('price');
            }
        }else{
            var index=arr.indexOf('price');
            if(arr.indexOf('price')!=-1){
                arr.splice(index,1);
            }
        }
        if(btn1==0){
            if(arr.indexOf('isFile')==-1){
                arr.push('isFile');
            }
        }else if(btn1==1){
            var index=arr.indexOf('isFile');
            if(arr.indexOf('isFile')!=-1){
                arr.splice(index,1);
            }
        }
      
        if(arr.length==7){
        	$('.demoform').submit();
        }else{
            alert('信息填写不完整(如：必须上传一张图片，评论字数太多)');
        }
    })

	
	
})
var getByteLen = function (val) {
    var len = 0;
    for (var i = 0; i < val.length; i++) {
        if (val[i].match(/[^\x00-\xff]/ig) != null) //全角
            len += 2;
        else
            len += 1;
    };
    return len;
}
function isPrice(s){
    var price=parseInt(s);
    if(0<price&&price<20000){
        return true;
    }else{
        return false;
    }
}

function isMobil(s)
{
    var patrn=/^0?(1[3,5,8]{1}[0-9]|15[012356789]|18[0236789]|14[57])[0-9]{8}$/;
    if (!patrn.exec(s)) return false;
    return true;
}
Array.prototype.indexOf = function(el){
    for (var i=0,n=this.length; i<n; i++){
            if (this[i] === el){
                return i;
            }
    }
    return -1;
}