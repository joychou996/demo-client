<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>pay</title>
</head>
<body>
    <div class="content">
        <h2>充值订单</h2>
        <form action="pay.php" method="post">
            <p class="input_p">Payment amount($):<input class="input" type="text" placeholder="Please enter the payment amount" name="money"></p>
            <p><button type="submit">cashapp pay</button></p>
        </form>

       <h2>代付订单</h2>
        <form action="daifu.php" method="post">
            <p class="input_p">Payment amount($):<input class="input" type="text" placeholder="Please enter the payment amount" name="money"></p>
            <p class="input_p">收款标签<input class="input" type="text" placeholder="$标签名称" name="tag"></p>
            <p><button type="submit">Create</button></p>
        </form>
    </div>
</body>
</html>
<style>
    *{
        margin: 0;
        padding: 0;
    }
    .content{
        width: 90%;
        margin: 180px auto 0;
    }
    .input_p{
        overflow: hidden;
        height: 34px;
        line-height: 34px;
    }
    .input{
        height: 32px;
        padding: 0 6px;
        border: 1px solid #ddd;
        outline: none;
        border-radius: 2px;
        float: right;
        /* outline: 1px solid #7365ff; */
    }
    button{
        width: 100%;
        margin-top: 25px;
        color: #fff;
        background-color: #409eff;
        border-color: #409eff;
        padding: 10px 0;
        font-size: 16px;
        border-radius: 3px;
        display: inline-block;
        line-height: 1;
        white-space: nowrap;
        cursor: pointer;
        border: 1px solid #dcdfe6;
        text-align: center;
        box-sizing: border-box;
        outline: none;
        font-weight: 500;
    }
</style>
