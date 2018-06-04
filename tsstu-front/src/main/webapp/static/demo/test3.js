function build_ma_data (count, data) {
    var result = [];
    for (var i = 0, len = data.length; i < len; i++) {
        if (i < count) {
            result.push('-');
            continue;
        }
        var sum = 0;
        
        for (var j = 0; j < count; j++) {
            sum += data[i - j][1];
        }
        result.push(sum / count);
    }
    return result;
}
    
function build_diff_data(m_short, m_long, data) {
    var result = [];
    var pre_emashort = 0;
    var pre_emalong = 0;
    for (var i = 0, len = data.length; i < len; i++) {
        var ema_short = data[i][1];
        var ema_long = data[i][1];
        
        if (i != 0) {
            ema_short = (1.0 / m_short) * data[i][1] + (1 - 1.0 / m_short) * pre_emashort;
            ema_long = (1.0 / m_long) * data[i][1] + (1 - 1.0 / m_long) * pre_emalong;
        }

        pre_emashort = ema_short;
        pre_emalong = ema_long;
        var diff = ema_short - ema_long;

        result.push(diff);
    }

    return result;
}
    
function build_dea_data(m, diff) {
    var result = [];
    var pre_ema_diff = 0;
    for (var i = 0, len = diff.length; i < len; i++) {
        var ema_diff = diff[i];
        
        if (i != 0) {
            ema_diff = (1.0 / m) * diff[i] + (1 - 1.0 / m) * pre_ema_diff;
        }
        pre_ema_diff = ema_diff;

        result.push(ema_diff);
    }

    return result;
}
    
function build_macd_data(data, diff, dea) {
    var result = [];
    
    for (var i = 0, len = data.length; i < len; i++) {
        var macd = 2 * (diff[i] - dea[i]);
        result.push(macd);
    }

    return result;
}

var QouteService = {};
this.mode = 0;
this.qoute_list = [];
this.trade_list = [];
this.category_list = [];

QouteService.get_qoute_list = function(){
	return QouteService.qoute_list
}

QouteService.qoute = function(mode, market, code) {
    return false;
};

QouteService.trade = function(mode, market, code) {
    return false;
};

QouteService.request_category = function(complete) {
    if (complete) {
        var categorys = [
            { "mode":2, "name": "按需"},
            { "mode":1, "name": "多空"},
        ]

    	QouteService.mode = categorys[0].mode;
        complete(categorys);
    }
};

QouteService.request_qoute = function(id, complete) {
    var url = AppConfigService.api_url + "qoute/get";
    $http.get(url, { 
        "timeout": 10000, 
        "params": { "id": id } 
    })
    
    .success(function(protocol) {
        if (protocol.return_code === "SUCCESS") {
            if (complete) {
                complete(protocol.data);
            }
        }
    });
}

QouteService.request_trades = function(mode, complete) {
    var tradesurl = AppConfigService.build_api_url("v1/trades");
    $http.get(tradesurl, {
    	"timeout": 10000 ,
    	"params": {"mode": mode}
	})
    
    .success(function(protocol) {
        if(complete) {
            complete(protocol); 
        }
    });
}

QouteService.request_qoute_list = function(mode, complete) {
   	var codes = QouteService.trade_list.map(function(value) {
        return value.market + ":" + value.code;
   });
    var url = AppConfigService.qoute_url + "last/"+ codes.join("|") + "?token=" + AppConfigService.token;
    $http.get(url, { 
    	"timeout": 10000 ,
    	"params": {"mode":mode}
	})
    
    .success(function(protocol) {
        if(complete) {
            complete(protocol);
        }
    });
}

var $scope = {};
function change_chart_data(history_list) {
    $scope.chart_data = history_list
    var dates = history_list.map(function(value) {
        return value.datetime;
    });

    var data = history_list.map(function(value) {
        return [ value.open, value.close, value.low, value.high];
    });
    if (data.length > 0) {
        data[data.length - 1][1] = $rootScope.qoute.value;
    }
   
    var line_data = history_list.map(function(value) {
        return value.close;
    });
    if (line_data.length > 0) {
        line_data[line_data.length - 1] = $rootScope.qoute.value;
    }
   

    var diff = build_diff_data(12, 26, data);
    var dea = build_dea_data(9, diff);
    var macd = build_macd_data(data, diff, dea);
    var m5 = build_ma_data(5, data);
    var m10 = build_ma_data(10, data);
    var m20 = build_ma_data(20, data);
    var m30 = build_ma_data(30, data);
    
    $scope.chart_type = "stock";
    $scope.diff = diff;
    $scope.dea = dea;
    $scope.macd = macd;

    $scope.chart_option = {
        animation: false,
        backgroundColor: 'rgb(25, 25, 26)',
        legend: {
            show: false,
        },
        tooltip: {
            show: false,
        },
        grid: [
            {
                top: 5+'%',
                bottom: 30+'%',
                left: 4+'%',
                right: 0+'%',
                height: 65+'%',
                containLabel:true,
            },
            {
                top: 75+'%',
                bottom: 0+'%',
                left: 2+'%',
                right: 0+'%',
                height: 25+'%',
                containLabel:true,
            },
        ],
        xAxis: [
            {
                gridIndex: 0,
                type: 'category',
                data: dates,
                axisLine: { 
                    show: false,
                },
                axisTick: {
                    show: false,
                },
                axisLabel: {
                    textStyle: { color: 'rgb(100, 100, 100)' },
                    formatter: function (value, index) {
                        if ($scope.chart_period == "d1") {
                            var time = value.split(" ")[0];
                            var split = time.split("-");
                            return split[1] + "/" + split[2];
                        }
                        else {
                            var time = value.split(" ")[1];
                            var split = time.split(":");
                            return split[0] + ":" + split[1];
                        }
                    }   
                },
            },
            {
                gridIndex: 1,
                type: 'category',
                data: dates,
                axisLine: { 
                    show: false,
                },
                axisTick: {
                    show: false,
                },
                axisLabel: {
                    show: false,
                },
            },
        ],
        yAxis: [
            {
                gridIndex: 0,
                position: "right",
                scale: true,
                axisLabel: {
                    textStyle: { color: 'rgb(100, 100, 100)' },
                    formatter: function (value, index) {
                        return value;
                    }   
                },
                axisLine: { 
                    show: false,
                },
                axisTick: {
                    show: false,
                },
                splitLine: { 
                    show: true,
                    lineStyle: {
                        color: 'rgb(35, 34, 38)',
                    }
                }
            },
            {
                gridIndex: 1,
                position: "right",
                scale: true,
                axisLabel: {
                    textStyle: { color: 'rgb(100, 100, 100)' },
                    formatter: function (value, index) {
                        if (value >= 0) {
                            return "+" + value.toFixed(4);
                        }
                        return value.toFixed(4);
                    }   
                },
                axisLine: { 
                    show: false,
                },
                axisTick: {
                    show: false,
                },
                splitLine: { show: false }
            },
        ],
        dataZoom: [
            { 
                xAxisIndex: [0, 1], 
                type : 'inside' },
        ],
        series: [
            {   
                name: 'line',
                type: 'line',
                xAxisIndex: 0,
                yAxisIndex: 0,
                data: $scope.chart_type === "line" ? line_data : [], 
                showSymbol: false,
                lineStyle: {
                    normal: {
                        width: 1,
                        color: 'rgb(253, 209, 42)',
                    }   
                },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgb(87, 72, 38)'
                        }, {
                            offset: 1,
                            color: 'rgb(47, 44, 40)'
                        }])
                    },
                },
                markPoint: {
                    symbol: "rect",
                    animation: true,
                    symbolSize: [60, 18],
                    symbolOffset: [-20, 0],
                    data: [
                    { 
                        name: '最新价', 
                        x: '100%',
                        yAxis: line_data[line_data.length - 1],
                        value: line_data[line_data.length - 1],
                        label: {
                            normal: {
                            	show:true,
                                position: [ 0, 1 ],
                                textStyle: {
                                    color: "#FFFFFF",
                                },
                                formatter: function(params) {
                                    return params.value;
                                },
                            }
                        },
                    }
                    ]
                },
                markLine: {
                    symbolSize: 0,
                    animation: false,
                    label: {
                        normal: {
                            show: false,
                        }
                    },
                    lineStyle: {
                        normal: {
                            type: 'dashed',
                            width: 1,
                        },
                    },
                    data: [
                        [
                        {
                            name: "rootScope.qoute.value",
                            x: 0,
                            yAxis: line_data[line_data.length - 1],
                        },
                        {
                        	name: "rootScope.qoute.value",
                            x: '100%',
                            yAxis: line_data[line_data.length - 1],
                        }
                        ],
                    ]
                },
            },  
            {
                name: 'stick',
                xAxisIndex: 0,
                yAxisIndex: 0,
                type: 'candlestick',
                data: $scope.chart_type === "stock" ? data : [],
                itemStyle: {
                    normal: {
                        color: 'rgb(25, 25, 26)',
                        color0: 'rgb(19, 233, 236)',
                        borderColor: 'rgb(250, 46, 66)',
                        borderColor0: 'rgb(19, 233, 236)',
                    }
                }
            },
            {
                name: 'ma5',
                type: 'line',
                xAxisIndex: 0,
                yAxisIndex: 0,
                data: $scope.chart_type === "stock" ? m5 : [],
                smooth: true,
                showSymbol: false,
                lineStyle: {
                    normal: {
                        width: 1
                    }
                }
            },
            {
                name: 'ma10',
                type: 'line',
                xAxisIndex: 0,
                yAxisIndex: 0,
                data: $scope.chart_type === "stock" ? m10 : [],
                smooth: true,
                showSymbol: false,
                lineStyle: {
                    normal: {
                        width: 1,
                        color: '#86da2b'
                    }
                }
            },
            {
                name: 'ma20',
                type: 'line',
                xAxisIndex: 0,
                yAxisIndex: 0,
                data: $scope.chart_type === "stock" ? m20 : [],
                smooth: true,
                showSymbol: false,
                lineStyle: {
                    normal: {
                        width: 1,
                        color: '#ff5382'
                    }
                }
            },
            {
                name: 'ma30',
                type: 'line',
                xAxisIndex: 0,
                yAxisIndex: 0,
                data: $scope.chart_type === "stock" ? m30 : [],
                smooth: true,
                showSymbol: false,
                lineStyle: {
                    normal: {
                        width: 1,
                        color: '#3d8ef6'
                    }
                }
            },
            {
                name: 'diff',
                type: 'line',
                data: diff,
                smooth: true,
                showSymbol: false,
                xAxisIndex: 1,
                yAxisIndex: 1,
                lineStyle: {
                    normal: {
                        width: 1,
                        color: '#00ffff'
                    }
                }
            },
            {
                name: 'ema',
                type: 'line',
                data: dea,
                smooth: true,
                showSymbol: false,
                xAxisIndex: 1,
                yAxisIndex: 1,
                lineStyle: {
                    normal: {
                        width: 1,
                        color: '#fe337f'
                    }
                }
            },
            {
                name: 'macd',
                type: 'bar',
                xAxisIndex: 1,
                yAxisIndex: 1,
                itemStyle: {
                    normal: {
                        color: 'rgb(31, 198, 91)',
                        borderColor: 'black',
                    }
                },
                data: macd,
            },
        ]
    };
}
var history_list = {"market":"IB","code":"GBP.USD","data":[{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 16:11:00","open":1.23767,"high":1.23773,"low":1.23767,"close":1.23771},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 16:10:00","open":1.2377500000000001,"high":1.2378,"low":1.23763,"close":1.23773},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 16:09:00","open":1.23767,"high":1.2379200000000001,"low":1.23761,"close":1.23781},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 16:08:00","open":1.23795,"high":1.2380200000000001,"low":1.2377,"close":1.2377},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 16:07:00","open":1.23733,"high":1.23801,"low":1.23733,"close":1.23795},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 16:06:00","open":1.2379,"high":1.23805,"low":1.23732,"close":1.2373800000000001},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 16:05:00","open":1.23784,"high":1.23835,"low":1.23784,"close":1.23791},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 16:04:00","open":1.2384700000000002,"high":1.23862,"low":1.23784,"close":1.2378900000000002},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 16:03:00","open":1.23909,"high":1.23909,"low":1.2382900000000001,"close":1.2385300000000001},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 16:02:00","open":1.23923,"high":1.2394,"low":1.2390700000000001,"close":1.23915},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 16:01:00","open":1.23909,"high":1.23933,"low":1.23903,"close":1.23927},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 16:00:00","open":1.2390400000000001,"high":1.2394100000000001,"low":1.2387700000000001,"close":1.2392},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 15:59:00","open":1.23849,"high":1.23926,"low":1.23849,"close":1.2389700000000001},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 15:58:00","open":1.2382900000000001,"high":1.23861,"low":1.23815,"close":1.23854},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 15:57:00","open":1.23864,"high":1.23864,"low":1.23831,"close":1.2383600000000001},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 15:56:00","open":1.2388700000000001,"high":1.2390100000000002,"low":1.23864,"close":1.23865},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 15:55:00","open":1.23927,"high":1.23934,"low":1.23893,"close":1.23893},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 15:54:00","open":1.23937,"high":1.23937,"low":1.23929,"close":1.23932},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 15:53:00","open":1.2390800000000002,"high":1.23944,"low":1.2390400000000001,"close":1.23934},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 15:52:00","open":1.23926,"high":1.23929,"low":1.23903,"close":1.23913},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 15:51:00","open":1.2392,"high":1.2393500000000002,"low":1.23913,"close":1.23932},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 15:50:00","open":1.23902,"high":1.23932,"low":1.23895,"close":1.23926},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 15:49:00","open":1.23871,"high":1.2391,"low":1.23871,"close":1.23909},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 15:48:00","open":1.2389100000000002,"high":1.23909,"low":1.2385700000000002,"close":1.23876},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 15:47:00","open":1.2394100000000001,"high":1.2394500000000002,"low":1.23883,"close":1.2389700000000001},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 15:46:00","open":1.2394100000000001,"high":1.23959,"low":1.2394100000000001,"close":1.2394500000000002},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 15:45:00","open":1.2395100000000001,"high":1.23957,"low":1.23943,"close":1.2394500000000002},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 15:44:00","open":1.2394,"high":1.23957,"low":1.2394,"close":1.23956},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 15:43:00","open":1.23961,"high":1.23966,"low":1.23944,"close":1.23946},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 15:42:00","open":1.23891,"high":1.2396800000000001,"low":1.23884,"close":1.23967},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 15:41:00","open":1.23851,"high":1.2390599999999998,"low":1.23846,"close":1.2389599999999998},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 15:40:00","open":1.2384,"high":1.23865,"low":1.23836,"close":1.23855},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 15:39:00","open":1.23869,"high":1.2388800000000002,"low":1.23836,"close":1.23845},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 15:38:00","open":1.23877,"high":1.23894,"low":1.2387100000000002,"close":1.2387100000000002},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 15:37:00","open":1.23902,"high":1.2391400000000001,"low":1.23881,"close":1.2388299999999999},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 15:36:00","open":1.2389000000000001,"high":1.23908,"low":1.23883,"close":1.23908},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 15:35:00","open":1.2388000000000001,"high":1.2389700000000001,"low":1.23866,"close":1.23895},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 15:34:00","open":1.23843,"high":1.2392299999999998,"low":1.23843,"close":1.23885},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 15:33:00","open":1.23868,"high":1.2389299999999999,"low":1.23843,"close":1.23848},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 15:32:00","open":1.23933,"high":1.2395500000000002,"low":1.2385,"close":1.23874},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 15:31:00","open":1.2390800000000002,"high":1.2394200000000002,"low":1.23903,"close":1.2393800000000001},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 15:30:00","open":1.2389800000000002,"high":1.2393500000000002,"low":1.23882,"close":1.23913},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 15:29:00","open":1.23873,"high":1.23906,"low":1.2387000000000001,"close":1.23903},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 15:28:00","open":1.2390100000000002,"high":1.2390700000000001,"low":1.2387400000000002,"close":1.23878},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 15:27:00","open":1.23906,"high":1.23912,"low":1.23888,"close":1.2390700000000001},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 15:26:00","open":1.23875,"high":1.2390700000000001,"low":1.2387000000000001,"close":1.2390700000000001},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 15:25:00","open":1.23935,"high":1.23942,"low":1.23869,"close":1.2388000000000001},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 15:24:00","open":1.2393299999999998,"high":1.23943,"low":1.2391100000000002,"close":1.2393900000000002},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 15:23:00","open":1.23983,"high":1.23993,"low":1.23936,"close":1.23937},{"market":"IB","code":"GBP.USD","datetime":"2017-05-23 15:22:00","open":1.23969,"high":1.23998,"low":1.2396800000000001,"close":1.23988}]};

$scope.chart_period = "m1";
$scope.chart_data = [];
$rootScope = {};
$stateParams = {};
$rootScope.trade_order_list = [];

$scope.mode = $stateParams.mode;
if ($scope.mode == "default") {
	$scope.mode = QouteService.category_list[0].mode;
}
$scope.market = $stateParams.market;
if ($scope.market == "default") {
	$scope.market = QouteService.qoute_list[0].market;
}
$scope.code = $stateParams.code;
if ($scope.code == "default") {
	$scope.code = QouteService.qoute_list[0].code;
}

$rootScope.qoute = QouteService.qoute($scope.mode, $scope.market, $scope.code);
$rootScope.trade = QouteService.trade($scope.mode, $scope.market, $scope.code);
//$scope.order_params.cycle = $rootScope.trade.cycle[0];
//$scope.order_params.amount = $rootScope.trade.amounts[0];
$scope.change_cycle = function(c) {
    $scope.order_params.cycle = c;
}

$scope.change_amount = function(a) {
    $scope.order_params.amount = a;
    $scope.order_params.other_amount = "";
}

var has_new_history = function(dt_now, dt_chart, period) {
    var sub = dt_now.getTime() - dt_chart.getTime();
    sub = sub / 1000;

    if (period == "m1") {
        if (sub / 60 >= 1) {
            return true;
        }
    }
    else if (period == "m5") {
        if (sub / 60 / 5 >= 1) {
            return true;
        }
    }
    else if (period == "m15") {
        if (sub / 60 / 15 >= 1) {
            return true;
        }
    }
    else if (period == "m30") {
        if (sub / 60 / 30 >= 1) {
            return true;
        }
    }
    else if (period == "h1") {
        if (sub / 60 / 60 >= 1) {
            return true;
        }
    }
    else if (period == "d1") {
        if (sub / 60 / 60 / 24 >= 1) {
            return true;
        }
    }

    return false;
}


change_chart_data(history_list.data);
var dom = document.getElementById("container");
var myChart = echarts.init(dom);
myChart.setOption($scope.chart_option, true);
