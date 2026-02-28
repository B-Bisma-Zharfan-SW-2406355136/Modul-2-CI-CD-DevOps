Reflection 1: Evaluasi Clean Code dan Secure Coding Practices

Setelah mengimplementasikan dua fitur baru menggunakan Spring Boot, yaitu Edit Product dan Delete Product, dilakukan evaluasi terhadap 
penerapan coding standards, prinsip clean code, serta secure coding practices yang telah dipelajari pada modul ini.

Penerapan Clean Code Principles
Source code telah menerapkan Separation of Concerns dengan baik melalui pemisahan layer Controller, Service, Repository, dan Model. 
Controller bertanggung jawab menangani request HTTP, Service menangani logika bisnis, dan Repository mengelola penyimpanan data produk. 
Pemisahan ini membuat kode lebih terstruktur dan mudah dipelihara. Penamaan kelas dan method sudah deskriptif dan konsisten, seperti 
updateProduct(), deleteProductById(), serta findById(), sehingga tujuan setiap method mudah dipahami. Prinsip Single 
Responsibility Principle juga diterapkan karena setiap kelas dan method memiliki satu tanggung jawab yang jelas. Penggunaan anotasi 
pring Boot seperti @Controller, @Service, dan @Repository membantu meningkatkan keterbacaan kode serta mengurangi boilerplate code.

Penerapan Secure Coding Practices
Pada fitur Edit Product, data produk yang diubah tidak dimanipulasi secara langsung di controller, melainkan melalui service. Hal ini
membantu menjaga konsistensi data dan mencegah akses langsung ke repository dari controller. Fitur Delete Product juga menggunakan 
identitas produk (UUID) untuk memastikan produk yang dihapus adalah produk yang benar. Selain itu, penghapusan produk dikonfirmasi 
melalui antarmuka pengguna (confirmation dialog), sehingga dapat mencegah penghapusan data secara tidak sengaja. Akses data tetap 
dilakukan melalui repository tanpa penggunaan query mentah, sehingga risiko SQL Injection dapat diminimalkan. Alur HTTP juga tetap 
terkontrol dengan baik melalui penggunaan redirect setelah operasi POST atau DELETE.

Evaluasi dan Perbaikan
Beberapa hal yang masih dapat ditingkatkan adalah belum adanya validasi input pada fitur Edit Product, seperti validasi nama produk 
kosong atau jumlah produk bernilai negatif. Hal ini dapat diperbaiki dengan menambahkan anotasi validasi seperti @NotBlank dan @Min.
Selain itu, aplikasi belum memiliki mekanisme autentikasi dan otorisasi. Semua pengguna masih dapat mengakses fitur edit dan delete, 
sehingga pada pengembangan selanjutnya dapat ditambahkan Spring Security untuk membatasi akses terhadap operasi sensitif.



Reflection 2: 
1. Setelah menulis unit test, saya merasa lebih percaya diri terhadap stabilitas dan kebenaran kode yang dibuat. Unit test membantu 
memastikan bahwa setiap fungsi bekerja sesuai dengan yang diharapkan serta memudahkan dalam mendeteksi kesalahan lebih awal sebelum aplikasi 
dijalankan secara keseluruhan. Selain itu, unit test juga mempermudah proses refactoring karena perubahan kode dapat segera diverifikasi melalui 
pengujian. Jumlah unit test dalam satu kelas tidak memiliki angka pasti, namun idealnya setiap method dan skenario penting memiliki setidaknya 
satu unit test. Untuk method yang kompleks, dibutuhkan beberapa unit test guna mencakup berbagai kondisi, seperti input valid, input tidak valid,
dan edge case. Untuk memastikan unit test yang dibuat sudah cukup, salah satu metrik yang dapat digunakan adalah code coverage. Code coverage 
menunjukkan seberapa besar bagian kode yang dieksekusi saat pengujian dijalankan, seperti statement coverage atau branch coverage. Meskipun code 
coverage tinggi (bahkan 100%), hal tersebut tidak menjamin bahwa kode bebas dari bug atau error. Code coverage hanya memastikan bahwa baris kode 
telah dieksekusi, bukan bahwa logika di dalamnya benar. Oleh karena itu, kualitas test case, variasi skenario, dan ketepatan assertion tetap 
menjadi faktor yang sangat penting.

2. Pada kasus pembuatan functional test baru untuk memverifikasi jumlah item pada product list, dengan struktur yang mirip dengan 
CreateProductFunctionalTest.java, terdapat potensi penurunan kualitas clean code jika tidak dikelola dengan baik. Masalah utama yang 
mungkin muncul adalah duplikasi kode, terutama pada bagian setup seperti inisialisasi WebDriver, URL, dan konfigurasi awal pengujian. 
Duplikasi ini melanggar prinsip DRY (Don’t Repeat Yourself) dan dapat menyulitkan maintenance. Jika terjadi perubahan pada konfigurasi, 
maka perubahan harus dilakukan di banyak tempat. Selain itu, adanya banyak kelas test dengan struktur serupa dapat menurunkan keterbacaan 
kode dan membuat project test menjadi kurang rapi. Hal ini juga bertentangan dengan prinsip Maintainability dalam clean code. Untuk 
meningkatkan kebersihan kode, solusi yang dapat diterapkan adalah membuat base test class (misalnya BaseFunctionalTest) yang berisi setup 
dan teardown umum. Kemudian, setiap functional test suite dapat melakukan inheritance dari base class tersebut. Alternatif lain adalah 
menggunakan utility atau helper class untuk menyimpan fungsi-fungsi yang sering digunakan. Dengan pendekatan ini, kode menjadi lebih modular, 
mudah dipelihara, dan lebih sesuai dengan prinsip clean code tanpa mengurangi fungsionalitas pengujian.


Reflection 3: CI/CD
1.  a. Issue: Low Code Coverage (Awalnya terdeteksi 0% pada SonarCloud Quality Gate).
       Strategy: 1. Menulis dan menambahkan Unit Test menggunakan framework JUnit dan Mockito (misalnya pada ProductControllerTest dan 
                    ProductServiceImplTest) untuk memastikan baris kode tereksekusi dan menguji berbagai skenario (baik skenario sukses maupun 
                    penanganan error).
                 2. Memperbaiki konfigurasi build tool pada build.gradle.kts agar task JaCoCo menghasilkan laporan dalam format XML (xml.required.set(true)).
                 3. Memperbaiki path di file konfigurasi CI (GitHub Actions) agar SonarCloud Scanner dapat menemukan file compiled classes 
                    (build/classes/java/main) dan laporan JaCoCo (jacocoTestReport.xml), sehingga metrik coverage terbaca dengan benar dan memenuhi standar 
                    Quality Gate (≥ 80%).
    b. Issue: "./gradlew test" Not Executable on CI
       Strategy: Memperbaiki bagian run di ci.yml sehingga membuat gradle bisa di execute terlebih dahulu, baru setelah itu di run

2. Ya, menurut saya implementasi saat ini sudah sepenuhnya memenuhi definisi dari Continuous Integration (CI) dan Continuous Deployment (CD).
Proses Continuous Integration terpenuhi melalui workflow GitHub Actions yang secara otomatis melakukan build, menjalankan unit test, dan menganalisis 
kualitas kode menggunakan SonarCloud setiap kali ada push atau pull request baru. Hal ini memastikan bahwa setiap kode yang digabungkan ke dalam repositori
telah terverifikasi fungsionalitas dan keamanannya tanpa perlu diuji secara manual.

Sementara itu, Continuous Deployment terpenuhi dengan menghubungkan repositori GitHub ke platform Koyeb menggunakan pendekatan pull-based. Platform Koyeb 
secara otomatis memantau branch main, sehingga ketika ada pembaruan kode yang berhasil melewati proses CI, sistem akan langsung men-deploy versi terbaru dari
aplikasi tersebut ke server production secara otomatis.

Reflection 4: SOLID Principle
## 1. Prinsip-prinsip yang Diterapkan pada Proyek
Dalam modul `Car` ini, saya menerapkan prinsip-prinsip **SOLID** dan arsitektur yang bersih (*Clean Architecture*) untuk menjaga kualitas kode. Berikut adalah prinsip-prinsip yang diterapkan:

* **Single Responsibility Principle (SRP):** Memisahkan tanggung jawab ke dalam kelas yang berbeda. `CarController` hanya mengurus HTTP request/response, `CarServiceImpl` mengurus logika bisnis, dan `CarRepository` khusus menangani akses data. Penggunaan **DTO (Data Transfer Object)** juga diterapkan agar model `Car` fokus sebagai representasi database, sementara input dari pengguna ditangani oleh `CarRequestDto`.
* **Open-Closed Principle (OCP):** Menggunakan *interface* seperti `CarService` dan `CarRepository`. Jika nantinya ada kebutuhan baru (misalnya menyimpan data ke database SQL), saya bisa membuat *class* baru yang mengimplementasikan `CarRepository` tanpa harus memodifikasi `CarServiceImpl` yang sudah ada.
* **Liskov Substitution Principle (LSP):** Memastikan bahwa `CarServiceImpl` benar-benar mengimplementasikan kontrak dari `CarService` dengan benar, sehingga objek ini dapat saling menggantikan tanpa memicu *error* pada aplikasi.
* **Interface Segregation Principle (ISP):** *Interface* seperti `CarService` dan `CarRepository` dirancang spesifik hanya untuk operasi yang berkaitan dengan entitas `Car`, tidak digabung dengan entitas lain (seperti `Product`).
* **Dependency Inversion Principle (DIP):** Modul tingkat tinggi (`CarServiceImpl`) tidak bergantung pada modul tingkat rendah (`CarRepositoryInMemory`), melainkan bergantung pada abstraksi/interface (`CarRepository`).

---

## 2. Keuntungan Menerapkan Prinsip SOLID (Beserta Contoh)
Penerapan SOLID memberikan dampak positif pada fleksibilitas dan pemeliharaan sistem:

* **Mudah Mengganti Teknologi (Fleksibilitas):** Karena `CarServiceImpl` bergantung pada *interface* `CarRepository` (DIP), jika besok saya ingin mengganti penyimpanan dari *In-Memory* ke *PostgreSQL*, saya cukup membuat `CarRepositoryPostgres`. Saya **tidak perlu mengubah satu baris kode pun** di dalam `CarServiceImpl`.
* **Keamanan dan Kerapian Data (Keamanan):** Dengan menerapkan SRP menggunakan `CarRequestDto`, saya mencegah *Mass Assignment Vulnerability*. Pengguna tidak bisa memanipulasi `carId` lewat form input karena DTO hanya menerima `carName` dan `carQuantity`.
* **Mudah Diuji (Testability):** Pemisahan *interface* membuat pembuatan *Unit Test* menjadi sangat mudah. Saya bisa memalsukan (*mock*) `CarRepository` saat melakukan *testing* pada `CarServiceImpl`.

---

## 3. Kerugian Tidak Menerapkan Prinsip SOLID (Beserta Contoh)
Jika kode ditulis secara sembarangan tanpa SOLID, aplikasi akan menjadi kaku (*Rigid*) dan rentan rusak (*Fragile*):

* **Spaghetti Code dan Sulit Dimodifikasi (Melanggar DIP & OCP):** Jika `CarServiceImpl` memanggil `new CarRepositoryInMemory()` secara langsung, maka Service dan Repository menjadi sangat terikat (*tight coupling*). Jika struktur In-Memory diubah, logika Service bisa ikut rusak, dan mengganti database berarti harus menulis ulang *class* Service.
* **God Object yang Berbahaya (Melanggar SRP):** Jika kita tidak menggunakan DTO dan memakai model `Car` untuk segalanya (database, validasi form, JSON response), kelas `Car` akan menjadi *God Object* yang penuh dengan ratusan baris anotasi (`@Table`, `@NotBlank`, `@JsonProperty`). Jika kita menambahkan *field* rahasia di database, *field* itu berisiko bocor ke *response* API publik karena tidak ada pemisah.
* **Duplikasi Kode yang Rentan Bug:** Jika logika pembuatan ID (`UUID.randomUUID()`) diletakkan berulang kali di berbagai metode Service, saat ada perubahan format ID, kita harus memburu dan mengubah semua baris kode tersebut satu per satu.