/*
	  思路：
			第一步：获取所要操作的节点对象
			第二步：当页面加载完后，需要计算本地cookie存了多少【个】商品，把个数赋值给ccount
			第三步：为每一个商品对应的添加购物车按钮绑定一个点击事件onclick
				 更改本地的cookie
				获取当前商品的pid
				循环遍历本地的cookie转换后的数组，取出每一个对象的pid进行对比，若相等则该商品不是第一次添加
				从购物车中取出该商品，然后更pCount值追加1
				否则：创建一个新的对象，保存到购物中。同时该商品的数量为1
 */
var item_input_qty = document.getElementById('item-input-qty');//控制輸入數量
var ccount = document.getElementById("ccount"); //显示商品总数量的标签节点对象
var btns = document.querySelectorAll(".item-cart-btn-join"); //所有的购物车按钮

//约定好用名称为datas的cookie来存放购物车里的数据信息  datas里所存放的就是一个json字符串
var listStr = cookieObj.get("datas");
/*判断一下本地是否有一个购物车（datas），没有的话，创建一个空的购物车，有的话就直接拿来使用*/

if (!listStr) { //没有购物车     datas  json
	cookieObj.set({
		name: "datas",
		value: "[]"
	});
	listStr = cookieObj.get("datas");
}

var listObj = JSON.parse(listStr); //数组
/*循环遍历数组，获取每一个对象中的pCount值相加总和*/
var totalCount = 0; //默认为0
for (var i = 0, len = listObj.length; i < len; i++) {
	totalCount = listObj[i].pCount + totalCount;
	// console.log(item_input_qty.value); 會報錯無法顯示數量
}
// ccount.innerHTML = totalCount ;
// console.log(listObj.length);
window.onload=function(){
	if (listObj.length > 0) {
		ccount.style.visibility='visible';
		ccount.innerHTML = listObj.length;
	}
	else{
		ccount.style.visibility='hidden';
	}
	// console.log(listObj.length);
}



/*循环为每一个按钮添加点击事件*/
for (var i = 0, len = btns.length; i < len; i++) {
	btns[i].onclick = function () {
		ccount.style.visibility='visible';
		ccount.innerHTML = listObj.length;
		var dl = this.parentNode.parentNode.parentNode.parentNode
		var pid = dl.getAttribute("pid");//获取自定义属性
		var arrs = dl.children;//获取所有子节点
		// console.log(arrs[1].children[0].children[1].innerHTML)
		
//顯示成功加入購物車資訊
		var obj = document.getElementById("success-msg");
            obj.style.opacity = '1';
			obj.style.zIndex = '1';
            window.setTimeout(
                function removethis() {
					var obj = document.getElementById("success-msg");
                    obj.style.opacity = '0';
					obj.style.zIndex = '-1';
                }, 1000);
				

//把數量回傳至上方
		if (checkObjByPid(pid)) {
			listObj = updateObjById(pid, parseInt(item_input_qty.value))
			console.log(listObj.length)
		} else {
			var imgSrc = arrs[0].children[0].firstElementChild.children[0].src
			var pName = arrs[1].children[0].innerHTML
			var pDesc = arrs[1].children[0].children[0].innerHTML
			var pDesc_type = arrs[1].children[8].children[0].children[1].innerHTML
			var price = arrs[1].children[2].children[0].children[1].innerHTML
			var obj = {
				pid: pid,
				pImg: imgSrc,
				pName: pName,
				pDesc: pDesc,
				pDesc_type:pDesc_type,
				price: price,
				pCount: parseInt(item_input_qty.value)
			};
			listObj.push(obj)
			listObj = updateData(listObj);
		}
		//在圖示上如果id重複,id數量不會疊加
		ccount.innerHTML = listObj.length;
		//以下會疊加
		// ccount.innerHTML = getTotalCount();
	}
}


