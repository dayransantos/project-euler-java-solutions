require 'digest/sha2';
require 'base64';

class Object;
  if !method_defined?(:-@);
    alias :-@ :class;
  end;
end
class Class;
  if !method_defined?(:_);
    alias :_ :new;
  end;
  if !method_defined?(:_!);
    alias :_! :superclass;
  end
end
module Kernel;
  if !method_defined?(:_?);
    alias :_? :catch;
  end;
  if !method_defined?(:_!);
    alias :_! :throw;
  end;
end

class Numeric
  if !method_defined?(:__);
    alias :__ :floor;
  end;
end

class Fixnum;
  if !method_defined?(:n!);
    alias :n! :*;
  end;
  if !method_defined?(:s);
    alias :s :to_s;
  end;
  if !method_defined?(:_);
    alias :_ :s;
  end;
  def *(x)
    x=x.is_a?(String) ? x.to_i(36) : x;
    n!(x);
  end
end

class Bignum;
  if !method_defined?(:n!);
    alias :n! :*;
  end;
  if !method_defined?(:s);
    alias :s :to_s;
  end;
  if !method_defined?(:_);
    alias :_ :s;
  end;
end

class String;
  if !method_defined?(:i);
    alias:i:to_i;
  end;
  if !method_defined?(:/);
    alias:/:split;
  end;
  if !method_defined?(:_);
    alias:_:i;
  end;
  if !method_defined?(:r);
      alias :r :reverse;
  end;
  if !method_defined? (:s);
    alias :s :size;
  end
end

module Enumerable;
  if !method_defined?(:_);
    alias :_ :map;
  end;
  if !method_defined?(:_?);
    alias :_? :inject;
  end
end


def pln(arg)
  print arg
  puts
end

def digit v; # name was _
  # A..Z -> 0..25, a..z - > 26..51
  (65..90) === v.ord() ? v.ord()-65 : v.ord() - 71;
end;

data = DATA.read.scan(/../)
    .map{|x| x.scan(/./)}
    .map{|a,b| v=52*digit(a) + digit(b)}
    .map{|x| x < 128 ? x.chr() : x};
    
# print data
# puts ""

s = 128;
d = Hash[Array.new(s) {|x| [x.chr] * 2}]; # [["\x00", "\x00"], ["\x01", "\x01"], ["\x02", "\x02"], ...

w = r = data.shift; # =="v"

for k in data;
  if d.has_key? k;
    e = d[k];
  else
    e = w + w[0];
  end;
  r += e;
  d[s] = w + e[0];
  s += 1;
  w = e;
end;

# File.open("D:\\some.rb", 'w') {|f| f.write(r) }

# ss = "def f(x); #" + "{r}" + "; end "
# puts ss
# eval ss

# regexp12 = Regexp.new(
        # Array.new(
          # 6,
          # %([A-F0123456789{8}])
        # ).join('-')
      # )

# regexp12 = Regexp.new(%([A-F0123456789]{8}-[A-F0123456789]{8}-[A-F0123456789]{8}-[A-F0123456789]{8}-[A-F0123456789]{8}-[A-F0123456789]{8}))
# pln regexp12      
# pln "12345678-12345678-12345678-12345678-12345678-12345678" =~ regexp12

def f(x);
  v=%w{0NXZnlGZ4VGauITM1EESTpjO0NXZnlGR 5292d91f914a3ed4 eb1496ac};
  def c(f,&block)
    ->{
        f[];
        block[]
      };
  end;
  
  def n(f, &block)
    ->(arg) {
        f[block[arg]]
    };
  end;
  
  alias :_ :eval;
  
  def t(n, &block)
    n > 0 ? c(t(~-n, &block), &block) : ->{};
  end;
  
  _ = n(->(_){_"#{_}"}) {|_| eval _} ['method(__method__).parameters[0][->(a,b){t(b.*b<=>0){a=b>0?-~a :~-a}[];a;}[4,-5]]'].to_s.strip;
  # _ is just an alias for x   

  y = 42;
  b = 32;

  t = _'self'; # => Object main 
  
  def t.z(arg)
    arg.to_s(32)
  end;
  
  def t.[]=(w,h,c)
  
    swclass = w.class.to_s
    
    pln swclass
    
    _ = swclass[0].downcase() + swclass[1..-1];
    
    pln "#{_} #{w};if !#{z 0x2ceec70d}_#{z 0x35cf95dcd}?(:#{h});#{z 0xaac95c}:#{h}:#{c};end;end"
    
    eval("#{_} #{w};if !#{z 0x2ceec70d}_#{z 0x35cf95dcd}?(:#{h});#{z 0xaac95c}:#{h}:#{c};end;end");
  end;

  y = y && t;
  k = ->{-t}[].included_modules[-1] # k == Kernel
  
  catch :! do;
    i = 1024.class;

    e = %%%
    o = Array
    
    e_ = (-[]).included_modules[@_.to_i]; # Enumerable class

    throw :! unless(
        # pln _
        # pln _.class
        # pln _.size
        if _.size == 040 + 12
          regexp = Regexp.new(
                  Array.new(
                    5,
                    %([A-F#{((->(_,o){x=0;t(o.*o<=>0){x=->(_,o){t(o.*o<=>0){_=o>0?-~_ :~-_}[];_;}[x,_]}[];x.*o<=>0}[b>>1,3])...(->(n,_){t(_.*_<=>0){n=_>0?-~n :~-n}[];n;}[b,[*%[a]..%[z]].size]))._(&:chr)*e}]{8})
                  ).join('-')
                )
          # pln regexp
          _ =~ regexp
        end
    );
  
    pln "after throw"
  
    c,d,e,x64=(%[132822049137759150859380516548]/%|9|)._{|_| _._}._{|_| _._ b};

    o = [];
    
    r = (281*%>v8ndb><<1)._(1<<5) # "require"
    
    catch :^ do;
      (o[0],o[1],o[2],o[3],o[4]) = (_/%{-}).map{|_| _.to_i(16) };
      pln v
      v = v.map{|arg| arg.reverse};
      
      h = "Base64.decode64('#{v[0]}')";
      
      def p(n)
        %r@^1?$|^(11+?)\1+$@!~'1'*n;
      end;
    
      # puts  eval('"'+%<;'}46x{#'}rhc.b{#}r{#;'})b(_.432539{#/})b(_.)'ho98'*1811({#'}rhc.b{#}r{#>.r+'"')
      # _ _('"'+%<;'}46x{#'}rhc.b{#}r{#;'})b(_.432539{#/})b(_.)'ho98'*1811({#'}rhc.b{#}r{#>.r+'"');
#       
      # puts eval(%<"#{'}e{#;]2/_,2/_-[))2(_.v(})h(_{#;)_,v(c }d{#'.r}">)
      # _ _(%<"#{'}e{#;]2/_,2/_-[))2(_.v(})h(_{#;)_,v(c }d{#'.r}">);

      def c(v,_);
        pln v._(2)
        r = Digest::SHA512.hexdigest(v.to_s(2))[-16,16];
        pln r
      end
      pln v
      pln v.[](1)
      
      throw :^ if (c(o[0],b) != v.[](1) );
      
      # puts "#{d} c(_);a=13;k=3;t=k;z=a;while a<_(%(_))&&_>z do k+=2;next if !->(_){p(_)}[k];a+=k*k;return _ if(_._=~((-//)._(t._*5))&&a==_);#{e};@_;#{e}"
      # _"#{d} c(_);a=13;k=3;t=k;z=a;while a<_(%(_))&&_>z do k+=2;next if !->(_){p(_)}[k];a+=k*k;return _ if(_._=~((-//)._(t._*5))&&a==_);#{e};@_;#{e}";
      def c(_);a=13;k=3;t=k;z=a;while a<_(%(_))&&_>z do k+=2;next if !->(_){p(_)}[k];a+=k*k;return _ if(_._=~((-//)._(t._*5))&&a==_);end;@_;end
      
      throw :^ if(!c o[1]);
        
      throw :! if v[2]._(b/2)!=o[2]||_('(1..~-b)._?(&:*)./(6**b)').__!=o[3];

      # puts _('"'+'}e{#;^:c%_;)d%,]b%b0[oc%cc%(}b-~_.375{#;}e{#;%%k%%q%%==]3[)b(_._&&3>s.)b(_._&&r.)b(_._==)b(_._&&_==r*r*r;__.)s%+)s%(**_(=r;)b,_(c }d{#'.r+'"')%[%%1.0/3%,'%d.%s'%[b^(1<<5),%'5'],-~b,b,b>>3,b,-~b]
      # _(_('"'+'}e{#;^:c%_;)d%,]b%b0[oc%cc%(}b-~_.375{#;}e{#;%%k%%q%%==]3[)b(_._&&3>s.)b(_._&&r.)b(_._==)b(_._&&_==r*r*r;__.)s%+)s%(**_(=r;)b,_(c }d{#'.r+'"')%[%%1.0/3%,'%d.%s'%[b^(1<<5),%'5'],-~b,b,b>>3,b,-~b]);
      def c(_,b);r=(_**(1.0/3)+0.5).__;r*r*r==_&&_._(b)==_._(b).r&&_._(b).s>3&&_._(b)[3]==%q%k%;end;if(!c o[0b100],32);_!:^;end

      !!b;
    end;
  end
end
puts "1st call"
f("FFFFFFFF-12345678-12345678-12345678-12345678")

puts "2nd call"
f("12312323-12312323-56745672-12345678-12345678")

__END__
COBJAlCPCTAwBaBkBmCGCEBTBmBABiBTBtCNBVBgBZAxBRBRBfBgCICCBbCdCfChBTBeAgBBAyBFAyBwBFAxByDMBABtAzBxBwBAAgBxBuAxBABFBCBtBvCVBHBwBxByAgBvAgByAsAmCLBHAtBKCTByBnBpBHCLDwDfBxCGBwDgDiAgCGDmDoDqDsAoBrApDuBnDzBrBpBpEBEDBHBtCECBBtCLBGBrBGBxCOEZEFDjCMEHDoBuBHCGBKAwBLBvAoCMAoCWAtCGEoApFCBGDsCTEWEEBrBJCGAoEMEOCTBrAiAjFQCVAiCVEPCUBrFZAgBrCVBnAnCFBxCMCACHBwENBrFhFjFlBrEOAuCIBtCKBtFpBxCKDzEzAwAtCWAwBpBnEMBtAsBuEPExBuAuAqBuBIBJEsEPBtBJBuEsBLGFBtAgBGEzBtFeDxBtBHFeBAAsAtBBEUAnBpAuCMCHBrCLAuCLCMCKCBCIBHCRBJBAAyAgBtEDAgBuBJGKGMEPCKBJAwBHGPGRGTGVAwHTHQBKAoGLGNCTHYGSGUGWCTGYGaEtGdGfGhGjEYGmBnCKAsBtEVDwBHCKHZHmAwFeAxBIBIFNHfBrAsCJGOAoCJGRCJIHEPFKCJGbGFBrHtAtFdIDBrHxGFAwGpGGBpFDIKBICRAvINENAsCHEPCQHVHXAoCHGRCHIXCTIzHRHiHkHaHnHpIbCWGeGgAtGiIDGlFeCQAsETHvCQJEIHFeAyAsEzAoIqAzApIoDxCMBJBrAnCLBxCEByAnEkAgCMAuCSFcDrIOIYGvGxCLJjILFOIwIyJAExJDAqJFHbKOKMIxHjJCJdKUFQBJCHJPIdJSIgDxIiJXJZICDxJcKSJeBnAxGpIOKNKYKRKTHnFKKeHrCWKgEzKiBHKkKsAsBAEUApFeETBHECEEDhElAuDwBJAgCPAsCAAsBvLIBJBnCPAuBvCEEcCLKjJqAuCFBtCIAgAmBGGwGyLfFOEPFSCTENAuCHCKBwArEyAwAmIqAoAyArJlApApLjCACKCVFTAnAnMXFQGJGGAuAuGDEVFWBnBrGJKvKXIzHWKQKaHnJIMnISKyJGLBKfIeLHLJJYJaIDKpKzIIMmIvMoKPKZKqKbMuNLMwMsHcKcLCGcLENBHvNDKmJbNUFeAwCQHJAsAxBpAsGHEULIAoMCFdAgFTCPDfCBDjAhFTKCNhAyBvBxBxBvBDAwBwCVBrOBAgNhAzBBBvDOBBBwBvOLBLAoBGFTCAFXBHOONhBtDdBFOSCVObCTOdOnDeLREXLSAiApOsEEHHCRAmAmCMKEAoGOBnKALkLmAsFFBMBpBJBGPJCLCLHvFGCMGJAtCMPLETPPCGBxNwIDCRLWPOAoCDHeCTPYGjAuCBCGLkCNDhBwFoFlCNCEBxGCAtGHPaBLPLBvBtCMBvCAOwBrBLBGAhBwCHBHCBHeAoBwHSJHNNGQHlNQQWQYJKAoQcJMNVBwGZJPBwIeOLIDEEKlBwKnBHKpQkFeBuGMGuPRDqKALXAgCDPaAhPLFjCKCHCPHXPiBJAoCBPLCGRLBGAqOwBxCZAlAlHLHNCHHQDwRgQpLUJzAuCAENQNAuBxDdCACTCUCJAsBxCUJtJvRSAoBxIQAsAlPHGxFTCJCVGuLxCLCRCFLOOxRRRqCTCBFFAlBKBKAsEdCBOeCRGJAlBISeBGAvPOBGCLCIEaPDPWRVEeSqRFSkMZPaSqCBLRFKFGAtEADwPqPsCEPuDTPxBwPzQBBnBMBrKHBrCBKjAhQQCNChQBKJTXCLCBCSRcHVBAAwArEMQgKOMqQiIGQeTrQZJLJGQmHqNYQpJSQrDxQtBnJYQvNeQdEsFeKWNTNPLAKdNAKhNbHxKtAyNmGFURKxNUIYUVLDLFIfUYJfNkEUPBEwBnBxIPSyPPLrCIApPBFKCWAoFNAvAvMTIdFNDwVHQiAvEMFBFPExTXAqBrJGCGJqJPEIJSCGHvCGHxEzGaBKJgMhSJAoBnBNAtBSFTVDUeMpJBMxTxMvUfUTNVMzUjNaIhHxNEQwNHKrVgKtAzIoMfAuIuVPISVSVUKbVWBrVYIeVbIDVdRAAsBnAqSKIBMfSKCSEUHATkBxIoVLLvQLCKApAqBxSPCTBEFXWvAnAtAnMSBHBvAsQYSHCQBCBARTSKAxAzAyBEAyAyAwDaXRBDBDBBDMBBAwBEXcAzBEAwBBAxBCBBBABEBpAvAlCUBFCUVHRxFaTXFdAuBrXyFZYCHODfPWAtPYRKPOAnAxBDBzCHBuCJBABvCHCMBCAnYCEyAtGNYbYeSRSTLIMCDeNuSZDfFTOLAgAqAoCQOwIzJcCBCLBrBtOZAtReAlApBLJcBrAoFTVOJZWSGRWUHnWWWYVaVcHxVgAzRCFXBGCQEqAhYtOwFTWxOfCTZiAiBHRiJDIiZJAlAkRnBzJzAsAqCEBHWfCEGuRuQLYEYJJjYCFDTXSRBnGHTYSSCFPOFdOxAkOwBzEwPLCKEhGAJtPLTjTlOwHUMOBEAxWgBKCOBEEDGaILAxVLIqBBApRlFcQPBqQSHXBnCBYCAhbGPaPbBGByCECHMGbDUtQFTECGCCOHPDJCaNNmCHKsbaBnUbIwBnWMbgLMApRTBrXsPnFXYCYEYAIuIPNMTuVvMtQWbvUSNIUhNXIcWDKjWFNdNGNfaNAxIlUeMrVzcEUWLGUmLKNlIoDfDhBvCZCUFTXMBAbZAuCNCIQIJtMbcgMdAwMfMhCVAuFTAzDZBDYGTyHiVtUAVwQXTzTvUOQlQnLDUFEzUHDgcJUMcLdJFeBBAsWMCVCHFTXWBFdAHfWRKYWTVVVXLDVZEzWaDxWcBnEzBEAsXqCVXNCUBHCYCObrFZYFMWBHCABJMCcbCVAoAnFTCObZCVXFZmRnLtVdAlCKBMBqAxBLAkCUBqJjAxArBLApBoehAkBMAhCWYOAnAqVdLSLIFceMAiAnArSlBHMaBABCCQCTAjMaCKCABvAuBufICVCKfIfDFXBuMEBAXSBBAzBFfIAvfVMEApAnFkBFBEesAxakAxAofIfKfMfOfQfSAjBKAuCKArAnezQNexSlMCMaBxfTBpAyAvIPgMBrTJMSAyMECOAoFXRqYDAjBHApIPgVDlCVBwfrfzFVBKQNTcBqNyAoEvbbQEGNAhBJeBDwJjXGFRYpCVDkRrEYBJXRBHPlAzHXBJCDBHCSBJGlCPCACBQAHLBIZrRrUzAmWXKCQSRIArBJAyEqBxCQEmNyAgAhMACTCIRrFeCDGkhhCDAqhOaaCMCNCKEIIdgpYABJVCVEVGYbKABrAqawhbGYJqZgZkDfTWZjWxZmBrgngpAhDlbbNlgmQQhoePUbYfAvAyApgvbbUbCUFZeMaIMgYdVHOZLvRaAuItBCAqGSfhVHidKdbhKjENeyfAgIfTBqPQAlIiApBwAlAsBpBuAlBuAwBnCHBvAlBvjlgWBuIcAuAzXbfTCVgJgaReCDReCJReBJBJBpAzBnApfWYAPBAzBKGzkJMEBrPBIFkQiJBJkVYCVAkECKAqkcCKLITXApCLAlArkiAlAojJZJBJkfkJJZgqAggggigAgCXFSKReAxAuAwAvAzjcAnAlBwAuAlCLlKBnBuefavFDAlAnBBGtImRBlbSdZYRBGFBuIoBHAhAhEpevLS