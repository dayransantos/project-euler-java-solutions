print'hello'

function testMapModif(p)
    map.p = 2;

end

function test(...)
    print (bit32.bnot(bit32.bnot(0)) == 0 and 1 or -1)
    map = {p = 7}
    print (map.p)
    testMapModif()
    print (map.p)

    for _ in
        function(p)
            print ("function " .. p.p)
    --        while p.p > 1
    --        do
    --            p.p, p = (function(p, arg2)
    --                while p % arg2 ~= 0 do arg2 = arg2 + 1 end
    --                return p / arg2, arg2
    --            end)(p.p, 2)
    --            return p
    --        end
            return nil
        end, { p = 9 }
    do
        print ("Do :" .. p)
        print (p);
        p = bit32.bnot(p)
    end
end

(test){ p = 24 }

function inner(a, b)
    while a % b ~= 0 do
        b = b + 1
    end
    return a / b, b
end

function f(...)
    pf = 2;
    p = 0
    repeat
        p = p +
                (
                    (
                        function(arg)
                            p = 0
                            for _ in
                                function(p)
                                    while p.p > 1 do
                                        p.p, p = inner(p.p, 2)
                                        return p
                                    end
                                    return nil
                                end, {p = arg }
                            do
                                p = bit32.bnot(p)
                            end
                            return p
                        end)
                    (pf) == 0 and 1 or -1
                );
        pf = pf + 1
    until p >= 0
    return pf - 1
end

(f){ p = 2 }