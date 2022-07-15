package com.indeed.vo.whiteboard;

/* =============================================================================
Question Description:
Python is validate by indent before each line of code, so given a list of Strings
which indicates the lines of Python code. Validate if it meets the requirement
=============================================================================*/
/* =============================================================================
code
=============================================================================*/

import java.util.Stack;

/**
 * =====================================================================================================================
 * 题目内容：
 *
 * 判断一段Python代码是否是合法的，会给出4个规则。
         *已知两个，1.第一行不能缩进。2.一行冒号结尾的code，下一行必须比这一行缩进的多。
         *主要是看缩进对不对。
         *要求返回第一个出错的行数。
         *照着规则去比对，其实stack一下就行，遇到伸出头的就一路弹出。就是把block给删除掉
         *猜测另外两个规则是和结尾有关，估计就是检查一下stack.size就好了。
  * =======================================================================================
  * 地里面经总结
  * =======================================================================================
         *<A> parse 一段Python代码，根据缩进规则，比如第一行不能有空格，前一行以冒号结尾的话，下一行空格数要比上一行多。。。
        *<B> python语言check，就是根据几条给定的rules，要求check一段python code缩进正确与否。
        *<C> 给一段Python代码，还有一些列规则，然后写程序检测该代码是否符合该规则（主要是Python的缩进规则）。
        *<D> 给了一些规则，但是记不起所有了，只记得2个规则是：
        *1.如果一行代码以':'（冒号）结尾，那么说明它是一个control statement，紧接着的下一行需要缩进（起码要比‘：’这一行要缩进）
        *2.代码开头这一行（第一行）不能缩进
        *<E> 大家都知道python是没有括号的，让写一个验证python语句是否合法。就是看缩进是不是正确
        *<F> 一道题，大概意思是给一些python代码的rule，全部符合就是valid的，否则就是invalid的，
        *给你一段代码（String的形式），判断是不是valid。不难。也问了下怎么优化，细节不太记得了
        *<G> validate python codes.Python is validated by the indents before each line of code,
        *so given a vector of strings(line of code),validate if it meets python requirement.
        *I remember there are 4basic rules provided.The solution is to use a stack.Not a hard problem.
  ===================================================================================================================**/

/***********************************************************************************************************************
 * python validation可以不用stack吧，只要判断：
 * 1）在冒号后的一行多缩进了一个空格
 * 2）不是冒号后的一行的缩进的空格数<=上一行的缩进空格数
 * 应该一个for loop就搞定了？
 *
 * *********************************************************************************************************************
 * 凯奇立刻出题，Python validation。终于知道这道题的四个规则是什么了。
 * 规则1，第一行的code必须没有缩进，
 * 规则2，尾巴是冒号的行一定是contarol statement，
 * 规则3，control statement下面一行一定要有更多的缩进，
 * 规则4，同一个block里面的缩进一定要相同。
 * 提出用stack来做，凯奇立刻问我为啥用stack呢，我就简单的讲了一下思路。回来在飞机上复盘的时候感觉失误在没有提出复杂度上优于brute force，
 * 只是讲了stack方法的思路可以搞定这道题，尤其是第四个规则。接着就开始写，边写边讲，讲自己每一行的代码都是组撒的，凯奇频频点头。
 * 写了三个玻璃之后，还自己跑了一遍test case，凯奇指着出题时候的case说跑一下这个，我像IDE一样逐行跑了一遍。凯奇拿起笔，
 * 写了个case说你看这‍‌个？
 * 先看了一眼时间，14点51。然后debug，凯奇看样子因为时间关系有些坐不住。我就直接照着case检查自己思路，三分钟不到的时间debug成功，
 * 把之前思路差错的地方说清楚，凯奇说你还有啥要问我的么。我当时刚刚debug成功，他突然这么一问我只能诚实回答说我刚做完题一下想不出问什么，
 * 而且也超时了一些，于是凯奇带着shadow逃窜了。
 *
 * *********************************************************************************************************************
 * python的缩进检查，两个白人小哥，一个挺帅的边问边做笔记，另外一个shadow没怎么问我问题我已经对他没什么印象了。。
 * 跟面经里的一模一样，小哥给你列一些规则，开头必须0 identation，然后control statement后面要缩进，这些楼主早已了然于心，
 * 还准备了很多特殊情况小哥说，这些都不会出现，你就按照最基本的做。 然后就是开始写题了，其实做法和大家都一样，不过可能有的人上来就说用stack，
 * 这样子有点假。我是说因为有nested control statements的情况，所以要记录之前的每一个缩进的数量，要选一个add tail比较高效的数据结构，
 * 大哥你觉得linkedlist 如何啊？ 小哥依旧严肃脸说，我觉得还可以，你写吧。然后就写完，分析时间空间复杂度，分析的时候千万不要背书冲口就爆一个数字，
 * 而是一行行分析，最后得出结论。 最后小哥follow up了我之前提到的特殊情况，想了一会也写出来了
 *
 * *********************************************************************************************************************
 * 上题。题目valid python，四个要求：
 * 1 首行无缩进
 * 2 冒号结尾的是control statement
 * 3 control statement后的句子缩进一定要比这句多
 * 4 同一个block里面的缩进必须相同。
 * 顺便提一句，这轮的interviewer应该是很多面筋里提到的爱记笔记的白人长发小哥。。。真的好帅啊！尤其是笑起来！霍霍霍霍霍。
 * 我感觉他准备题目准备的不太充分，lz这轮写的比较快，他就一直想方设法憋follow up，先是跑三行示例代码，再根据各种情况跑代码。。
 * 一直问你觉得啥case比较合适来测试这个code，然后我就跑了一个又一个case。。
 * 突然！他灵光一现，想到了一个之前怎么就是没想到明明一直都用这个的follow up之如果有独立一行的comment line怎么处理，lz准备过，秒了，
 * 然后他看时间还没到实在没招了又憋了个如果有inline comment line怎么改代码，巧了lz又准备过，于是再次秒。。then Q&A，二轮结束。
 * Preprocess the line - str.split("//")
 * single comment line: str.split("//") if the array size > 1 and array[0].trim()=""
 * inline comment line: str.split("//")
 *
 * *********************************************************************************************************************
 * 第一轮是验证python indentation. follow-up1: 在每行后面有个#评论，你怎么处理这种情况，不需要考虑空行或者整行是评论的。
 * follow-up2：如果匹配control block，比如上一行是if，下一行就得是else if or else。我当时没时间了，只考虑了最简单的情况，if else.
 *
 * 白板第三轮：python validation。 follow-up是 block comment, inline comment,
 * if-else, if-else if-else的处理方法。follow-up只要说说就行了。
 *
 *
 * 第二轮，白人长发小哥，之前看面经，有个人说有个白人小哥喜欢写note，估计就是他了。来了以后表情比较严肃，没什么笑脸。问了我实习的经历，问的非常细。
 * 我就把细节都说了一遍，感觉自己泄露了亚麻的机密哈哈。然后上题，stack判断python。我吸取了前人的经验，先分析了一下为什么stack比暴力解法要好，
 * 然后开始写代码。写的过程中写了个bug，被我自己发现了，改了过来。我是对着玻璃板边写边讲的，通过玻璃板的反光，我发现白人小哥完全没有抬头看我写代码，
 * 在低头写着什么。我没管，依然自己边写边讲。写完以后，小哥说，来，我给你个输入。我一想，这肯定是有bug了，不应该啊，这题我默写少说10遍了。
 * 结果小哥说，如果python代码只有一行，这一行最后以：结尾，你这代码怎么办。我一看，擦，真没办法handle啊，但是不难，多加了个if就好了。
 * 虽然是个小bug，但对于一个中国男生来讲，算是比较严重的错误了。
 */

public class Validate_Python_Indentation {
    public boolean validate(String[] lines){
        //就用stack来存之前的line就行
        Stack<String> stack = new Stack<>();
        for (String line : lines){
            int level = getIndent(line);
            //case 1: 先检查是不是第一行
            if (stack.isEmpty()){
                if (level != 0) {
                    System.out.println(line);
                    return false;
                }  //case 2: 再检查上一行是不是control statement
            } else if (stack.peek().charAt(stack.peek().length()-1) ==':'){
                if (getIndent(stack.peek()) + 1 != level){
                    System.out.println(line);
                    return false;
                }
            } else { //case 3: block lines: current line indent is less than previous line, pop up previous line until
                     // find the line in same block
                while (!stack.isEmpty() && getIndent(stack.peek()) > level){
                    stack.pop();
                }
                if (getIndent(stack.peek()) != level){
                    System.out.println(line);
                    return false;
                }
            }
            stack.push(line);
        }
        return true;
    }
    //这里如果它说n个空格算一次tab的话，就最后返回的时候res/n好了。
    public int getIndent(String line){
        int res = 0;
        for (int i = 0; i < line.length(); i++){
            if (line.charAt(i) == ' '){
                res++;
            }
            else break;
        }
        return res;
    }
    public static void main(String[] args) {
        Validate_Python_Indentation test = new Validate_Python_Indentation();
        String[] lines = {
                "def:",
                " abc:",
                "  b2c:",
                "   cc",
                " b5c",
                "b6c"
        };
        System.out.println(test.validate(lines));
        //先这样吧，应该行了。
    }
}
