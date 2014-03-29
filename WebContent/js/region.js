/**
 * 地域列表
 * @param slt <htmlElements : select>
 */
function initRegion(slt) {
	var url = "/region/list?" + new Date();
	$("<option selected=\"selected\" value='-1'>全部</option>").appendTo($(slt));
	$.getJSON(url, function(data) {
		$.each(data, function(i, item) {
			$("<option value=\""+item.regionId+"\">"+item.regionName+"</option>").appendTo($(slt));
		});
	});
}

