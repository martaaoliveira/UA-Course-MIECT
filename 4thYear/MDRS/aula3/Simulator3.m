function [PLdata , APDdata , MPDdata , PLvoip , APDvoip , MPDvoip , TT] = Simulator3(lambda,C,f,P,n)
% INPUT PARAMETERS:
%  lambda - packet rate (packets/sec)
%  C      - link bandwidth (Mbps)
%  f      - queue size (Bytes)
%  P      - number of packets (stopping criterium)
%  n      - number of VoIP packet flows

% OUTPUT PARAMETERS:
%  PLdata   - data packet loss (%)
%  APDdata  - average data packet delay (milliseconds)
%  MPDdata  - maximum data packet delay (milliseconds)
%  PLvoip   - voip packet loss (%)
%  APDvoip  - average voip packet delay (milliseconds)
%  MPDvoip  - maximum voip packet delay (milliseconds)
%  TT   - transmitted throughput (Mbps)

%Events:
ARRIVAL= 0;       % Arrival of a packet            
DEPARTURE= 1;     % Departure of a packet
DATA=0;
VOIP=1;
%State variables:
STATE = 0;          % 0 - connection free; 1 - connection busy
QUEUEOCCUPATION= 0; % Occupation of the queue (in Bytes)
QUEUE= [];          % Size and arriving time instant of each packet in the queue

%Statistical Counters:
TOTALPACKETS_D = 0;       % No. of data packets arrived to the system
LOSTPACKETS_D = 0;        % No. of data packets dropped due to buffer overflow
TRANSMITTEDPACKETS_D = 0; % No. of transmitted data packets
TRANSMITTEDBYTES_D = 0;   % Sum of the Bytes of transmitted data packets
DELAYS_D = 0;             % Sum of the DELAYS_D of transmitted data packets
MAXDELAY_D = 0;           % Maximum delay among all transmitted data packets
TOTALPACKETS_V = 0;       % No. of voip packets arrived to the system
LOSTPACKETS_V = 0;        % No. of voip packets dropped due to buffer overflow
TRANSMITTEDPACKETS_V = 0; % No. of transmitted voip packets
TRANSMITTEDBYTES_V = 0;   % Sum of the Bytes of transmitted voip packets
DELAYS_V = 0;             % Sum of the DELAYS_D of transmitted voip packets
MAXDELAY_V = 0;           % Maximum delay among all transmitted voip packets

% Initializing the simulation clock:
Clock= 0;

% Initializing the List of Events with the first ARRIVAL:
tmp= Clock + exprnd(1/lambda);
EventList = [ARRIVAL, tmp, GeneratePacketSize(), tmp, 0]; % last column represents the type of the packet (0 for data and 1 for voip)

% Generating VoIP packets
for i=1:n
    tmp = unifrnd(0, 0.02); % uniform distribution around the average inter arrival time of 20 milliseconds. 
    EventList = [EventList; ARRIVAL, tmp, randi([110, 130]), tmp, 1];
end

%Simulation loop:
while (TRANSMITTEDPACKETS_D + TRANSMITTEDPACKETS_V) < P               % Stopping criterium
    EventList= sortrows(EventList,2);    % Order EventList by time
    Event= EventList(1,1);               % Get first event and 
    Clock= EventList(1,2);               %   and
    PacketSize= EventList(1,3);          %   associated
    ArrivalInstant= EventList(1,4);      %   parameters.
    Type= EventList(1,5);                %
    EventList(1,:)= [];                  % Eliminate first event
    switch Event
        case ARRIVAL                     % If first event is an ARRIVAL
            if Type == DATA     % Data packet
                TOTALPACKETS_D= TOTALPACKETS_D+1;
                tmp= Clock + exprnd(1/lambda);
                EventList = [EventList; ARRIVAL, tmp, GeneratePacketSize(), tmp, DATA];
                if STATE==0
                    STATE= 1;
                    EventList = [EventList; DEPARTURE, Clock + 8*PacketSize/(C*10^6), PacketSize, Clock, DATA];
                else
                    if QUEUEOCCUPATION + PacketSize <= f
                        QUEUE= [QUEUE;PacketSize , Clock, 0];
                        QUEUEOCCUPATION= QUEUEOCCUPATION + PacketSize;
                    else
                        LOSTPACKETS_D= LOSTPACKETS_D + 1;
                    end
                end
            else    % VoIP packet
                TOTALPACKETS_V= TOTALPACKETS_V+1;
                tmp= Clock + unifrnd(0.016, 0.024);   % time between packet arrivals is uniformly distributed between 16 milliseconds and 24 milliseconds
                EventList = [EventList; ARRIVAL, tmp, randi([110, 130]), tmp, VOIP];
                if STATE==0
                    STATE= 1;
                    EventList = [EventList; DEPARTURE, Clock + 8*PacketSize/(C*10^6), PacketSize, Clock, VOIP];
                else
                    if QUEUEOCCUPATION + PacketSize <= f
                        QUEUE= [QUEUE;PacketSize , Clock, 1];
                        QUEUEOCCUPATION= QUEUEOCCUPATION + PacketSize;
                    else
                        LOSTPACKETS_V= LOSTPACKETS_V + 1;
                    end
                end
            end
            
        case DEPARTURE                     % If first event is a DEPARTURE
            if Type == DATA     % Data packet
                TRANSMITTEDBYTES_D= TRANSMITTEDBYTES_D + PacketSize;
                DELAYS_D= DELAYS_D + (Clock - ArrivalInstant);
                if Clock - ArrivalInstant > MAXDELAY_D
                    MAXDELAY_D= Clock - ArrivalInstant;
                end
                TRANSMITTEDPACKETS_D= TRANSMITTEDPACKETS_D + 1;
                if QUEUEOCCUPATION > 0
                    EventList = [EventList; DEPARTURE, Clock + 8*QUEUE(1,1)/(C*10^6), QUEUE(1,1), QUEUE(1,2), QUEUE(1,3)];
                    QUEUEOCCUPATION= QUEUEOCCUPATION - QUEUE(1,1);
                    QUEUE(1,:)= [];
                else
                    STATE= 0;
                end
            else    % VoIP packet
                TRANSMITTEDBYTES_V= TRANSMITTEDBYTES_V + PacketSize;
                DELAYS_V= DELAYS_V + (Clock - ArrivalInstant);
                if Clock - ArrivalInstant > MAXDELAY_V
                    MAXDELAY_V= Clock - ArrivalInstant;
                end
                TRANSMITTEDPACKETS_V= TRANSMITTEDPACKETS_V + 1;
                if QUEUEOCCUPATION > 0
                    EventList = [EventList; DEPARTURE, Clock + 8*QUEUE(1,1)/(C*10^6), QUEUE(1,1), QUEUE(1,2), QUEUE(1,3)];
                    QUEUEOCCUPATION= QUEUEOCCUPATION - QUEUE(1,1);
                    QUEUE(1,:)= [];
                else
                    STATE= 0;
                end
            end
    end
end

%Performance parameters determination:
PLdata= 100*LOSTPACKETS_D/TOTALPACKETS_D;      % in %
APDdata= 1000*DELAYS_D/TRANSMITTEDPACKETS_D;   % in milliseconds
MPDdata= 1000*MAXDELAY_D;                    % in milliseconds
PLvoip= 100*LOSTPACKETS_V/TOTALPACKETS_V;      % in %
APDvoip= 1000*DELAYS_V/TRANSMITTEDPACKETS_V;   % in milliseconds
MPDvoip= 1000*MAXDELAY_V;                    % in milliseconds
TT= 10^(-6)*(TRANSMITTEDBYTES_D + TRANSMITTEDBYTES_V)*8/Clock;  % in Mbps

end

function out= GeneratePacketSize()
    aux= rand();
    aux2= [65:109 111:1517];
    if aux <= 0.19
        out= 64;
    elseif aux <= 0.19 + 0.23
        out= 110;
    elseif aux <= 0.19 + 0.23 + 0.17
        out= 1518;
    else
        out = aux2(randi(length(aux2)));
    end
end