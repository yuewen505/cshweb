<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>后台登录-X-admin2.0</title>
        <meta name="renderer" content="webkit|ie-comp|ie-stand">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
        <meta http-equiv="Cache-Control" content="no-siteapp" />

        <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
        <link rel="stylesheet" href="./css/font.css">
        <link rel="stylesheet" href="./css/xadmin.css">
    </head>
    <body>
        <div class="x-body">
            <blockquote class="layui-elem-quote">
                统计所有订餐人员
            </blockquote>
            <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
            <div id="main" style="width: 100%;height:400px;"></div>
            <%--<blockquote class="layui-elem-quote">--%>
                <%--注意：本案例的Echarts图表库由cdn引入，需要在线才能正常使用，如想离想，请至Echarts官网下载。--%>
            <%--</blockquote>--%>
        </div>
        <script src="//cdn.bootcss.com/echarts/3.3.2/echarts.min.js" charset="utf-8"></script>
        <script src="//cdn.bootcss.com/echarts/3.3.2/extension/bmap.min.js" type="text/javascript"></script>
        <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
        var haveOrderCount=<%=request.getAttribute("haveOrder")%>
        var noOrderCount=<%=request.getAttribute("noOrder")%>

        // 指定图表的配置项和数据
        option = {
            backgroundColor: '#2c343c',

            title: {
                text: '订餐统计',
                left: 'center',
                top: 20,
                textStyle: {
                    color: '#ccc'
                }
            },

            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },

            visualMap: {
                show: false,
                min: 80,
                max: 600,
                inRange: {
                    colorLightness: [0, 1]
                }
            },
            series : [
                {
                    name:'访问来源',
                    type:'pie',
                    radius : '55%',
                    center: ['50%', '50%'],
                    data:[
                        {value:haveOrderCount, name:'一起订餐'},
                        {value:noOrderCount, name:'加班自己订餐'},
//                        {value:274, name:'联盟广告'},
//                        {value:235, name:'视频广告'},
//                        {value:400, name:'搜索引擎'}
                    ].sort(function (a, b) { return a.value - b.value}),
                    roseType: 'angle',
                    label: {
                        normal: {
                            textStyle: {
                                color: 'rgba(255, 255, 255, 0.3)'
                            }
                        }
                    },
                    labelLine: {
                        normal: {
                            lineStyle: {
                                color: 'rgba(255, 255, 255, 0.3)'
                            },
                            smooth: 0.2,
                            length: 10,
                            length2: 20
                        }
                    },
                    itemStyle: {
                        normal: {
                            color: '#c23531',
                            shadowBlur: 200,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };



        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>

    </body>
</html>