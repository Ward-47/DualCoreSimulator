/*
* Creating a program that simulates a Dual Core Simulator
* @author Ward Leavines
* @see DualCoreSimulator, HeapException, HeapAPI, Heap, PCB
*/

package dualcoresimulator;

/**
 * A simple non-preemptive dual-core CPU simulator
 * @since 9/24/17
 */

import java.util.Random;

public class DualCoreSimulator 
{
   public static void main(String []args) throws HeapException{
   
      
   Heap<PCB> CPU1 = new Heap(), CPU2 = new Heap();
   Heap[] DCS = {CPU1, CPU2};
   Random rand = new Random();
   
   
   
   rand.setSeed(System.currentTimeMillis());
   int cycle= 1, index, pid = 0, pid2 = 0, priority = 0, length = 0;
   double TP[] = {0,0}, WT[] = {0, 0};
   while(cycle <= Integer.parseInt(args[1]))
   {
        
	   System.out.println("*** Cycle #" + cycle);
   System.out.printf("CPU 1 (%d) CPU 2 (%d)\n", CPU1.size(), CPU2.size());
   index =1; 
   for (Heap<PCB> CPU : DCS)
   { 
       
       if (CPU.isEmpty())
           System.out.printf("CPU%d: idle. \n", index);
       else{
           if (CPU.peek ().isExecuting())
           {
               if (cycle - CPU.peek().getStart() >= CPU.peek().getLength())
               {
                   CPU.remove();
                   System.out.printf("CPU %d: Process #%d has just terminated. \n", index, CPU.peek().getPid());
                   TP[index -1]++; 
               }
               else 
               {
                   System.out.printf("CPU %d: Process #%d is executing.\n", index, CPU.peek().getPid());
               }
           }
           else{
               CPU.peek().execute();
               CPU.peek().setStart(cycle);
               CPU.peek().setWait(cycle - CPU.peek().getArrival());
               WT[index -1] = WT[index -1] + CPU.peek().getWait();
               System.out.printf("CPU %d: Process #%d is executig. \n", index, CPU.peek().getPid());
           }
       }
       index++;
   }
   double q = rand.nextDouble(), p = Double.parseDouble(args[0]);
   if (q <= Math.pow(p, 2))
   {
       pid++;
       priority = rand.nextInt (40) - 20;
       length = rand.nextInt(100) +1; 
       System.out.printf("Two new jobs this cycle.", pid, priority, length);
       if (CPU1.size() == CPU2.size())
       {
           CPU1.insert(new PCB(pid, priority, 0, cycle, length));
           System.out.printf("CPU 1: Adding job with pid #%d and priority %d and length %d. \n", pid, priority, length);
           pid++;
           priority = rand.nextInt(40) -20;
           length = rand.nextInt(100) +1; 
           CPU2.insert(new PCB(pid, priority, 0, cycle, length));
           System.out.printf("CPU 2: Adding job with pid #%d and priority %d and length %d. \n", pid, priority, length);
           
       }
       else if (CPU1.size() > CPU2.size()){
           CPU2.insert(new PCB(pid, priority, 0, cycle, length));
           System.out.printf("CPU 2: Adding job with pid #%d and priority %d and length %d. \n", pid, priority, length);
           pid++;
           priority = rand.nextInt(40) -20;
           length = rand.nextInt(100) +1; 
           CPU2.insert(new PCB(pid, priority, 0, cycle, length));
           System.out.printf("CPU 2: Adding job with pid #%d and priority %d and length %d. \n", pid, priority, length);
           
       }
       
       else 
       {
           CPU1.insert(new PCB(pid, priority, 0, cycle, length));
           System.out.printf("CPU 1: Adding job with pid #%d and priority %d and length %d. \n", pid, priority, length);
           pid++;
           priority = rand.nextInt(40) -20;
           length = rand.nextInt(100) +1; 
           CPU2.insert(new PCB(pid, priority, 0, cycle, length));
           System.out.printf("CPU 2: Adding job with pid #%d and priority %d and length %d. \n", pid, priority, length);
           
       }
   }
   else if (Math.pow(p, 2) < q && q <= p)
   {
       pid++;
       priority = rand.nextInt (40) - 20; 
       length = rand.nextInt(100) +1; 
       System.out.println("One new job this cycle.");
       if (CPU1.size() > CPU2.size())
       {
           CPU2.insert (new PCB(pid, priority, 0, cycle, length));
           System.out.printf("CPU 2: Adding job with pid #%d and priority %d and length %d\n", pid, priority, length);
       }
       else{
           CPU1.insert (new PCB(pid, priority, 0, cycle, length)); 
           System.out.printf("CPU 1: Adding job with pid #%d and priotiry %d and length %d\n", pid, priority, length);
       }
   }
   else 
       System.out.println("No new job this cycle");
       cycle++;
       
       
   }
   System.out.println();
       double avgThroughput1 = TP [0]/cycle, avgThroughtput2 = TP[1]/cycle, avgWaitTime1 = WT[0]/TP[0], avgWaitTime2 = WT[1]/TP[1];
       System.out.printf("CPU 1: average throughput is %.3f per cycle\n", avgThroughput1);
       System.out.printf("CPU 1: average wait time is %.3f per cycle\n", avgWaitTime1);
       System.out.printf("CPU 2: average throughput is %.3f per cycle \n", avgThroughtput2);
       System.out.printf("CPU 2: average wait time is %.3f per cycle\n", avgWaitTime2);
       System.out.printf("overall average throuput is %.3f per cycle \n", avgThroughput1 + avgThroughtput2);
       System.out.printf("overall average wait time is %.3f per cycle \n", (avgWaitTime1 + avgWaitTime2)/2);
   }
   
}
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
   