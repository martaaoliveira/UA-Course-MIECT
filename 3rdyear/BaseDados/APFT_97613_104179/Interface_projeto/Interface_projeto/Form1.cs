using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Data.SqlClient;
using static System.Windows.Forms.VisualStyles.VisualStyleElement;
using static System.Windows.Forms.VisualStyles.VisualStyleElement.ListView;
using System.Collections;
using System.Reflection.Emit;

namespace Interface_projeto
{
   
    public partial class Form1 : Form

    {
        public static string DB_CONNECTIONSTRING = @"Data Source=tcp:mednat.ieeta.pt\SQLSERVER,8101;Initial Catalog=p2g6;User ID=p2g6;Password=329624890@BDP2";
        private SqlConnection cn;
        private bool seePassword = false;
        public static string username = "";
        private string NIF_Comprador;  //  para conseguir aceder mais tarde
        private string game="";
        private bool mostrarGeneroFavorito = false;
        private string nifVendedor;
        private bool gamesBoughtClicked = false;
        bool evaluationHistoryClicked = false;
        private bool showingFavorites = false;

        public Form1()
        {
            InitializeComponent();
 
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            LoginPanel.Show();
            panelRegister.Hide();
            cn = getSGBDConnection();
            cn.Open();
           
        }

        private void CarregarDadosJogos()
        {
            using (SqlConnection connection = getSGBDConnection())
            {
                connection.Open();

                SqlCommand command1 = new SqlCommand("ONLINEGAMES_PLATFORM.GetJogos", connection);
                command1.CommandType = CommandType.StoredProcedure;

                DataTable dataTable = new DataTable();

                using (SqlDataReader reader = command1.ExecuteReader())
                {
                    dataTable.Load(reader);
                    dataGridView1.DataSource = dataTable;
                    

                }
            }
        }

        private void CarregarDadosJogosPorPlataforma(string plataforma)
        {
            using (SqlCommand cmd = new SqlCommand("ONLINEGAMES_PLATFORM.GetJogosPorPlataforma", cn))
            {
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("@NOME_PLATAFORMA", plataforma);
                SqlDataAdapter da = new SqlDataAdapter(cmd);
                DataTable dt = new DataTable();
                da.Fill(dt);
                dataGridView1.DataSource = dt;
                //dataGridView1.Columns[dataGridView1.Columns.Count - 1].AutoSizeMode = DataGridViewAutoSizeColumnMode.Fill;

            }
        }




        public bool UserExists(string email, string password, string nif)
        {
            using (SqlConnection connection = getSGBDConnection())
            {
                string query = "SELECT * FROM ONLINEGAMES_PLATFORM.UTILIZADOR WHERE Email = @Email OR NIF = @NIF";
                SqlCommand command = new SqlCommand(query, connection);
                command.Parameters.AddWithValue("@Email", email);
                command.Parameters.AddWithValue("@Password", password);
                command.Parameters.AddWithValue("@NIF", nif);
                connection.Open();
                SqlDataReader reader = command.ExecuteReader();
                return reader.HasRows;
            }
        }
        public bool InsertUser(string email, string password, string nif, string morada)
        {
            using (SqlConnection connection = getSGBDConnection())
            {
                connection.Open();
                SqlTransaction transaction = connection.BeginTransaction();

                try
                {
                    // Insere na tabela UTILIZADOR
                    string query1 = "INSERT INTO ONLINEGAMES_PLATFORM.UTILIZADOR (Email, Palavra_passe, NIF) VALUES (@Email, @Password, @NIF)";
                    SqlCommand command1 = new SqlCommand(query1, connection, transaction);
                    command1.Parameters.AddWithValue("@Email", email);
                    command1.Parameters.AddWithValue("@Password", password);
                    command1.Parameters.AddWithValue("@NIF", nif);
                    command1.ExecuteNonQuery();

                    // Insere na tabela Comprador
                    string query2 = "INSERT INTO ONLINEGAMES_PLATFORM.Comprador (MORADA, NIF) VALUES (@MORADA, @NIF)";
                    SqlCommand command2 = new SqlCommand(query2, connection, transaction);
                    command2.Parameters.AddWithValue("@MORADA", morada);
                    command2.Parameters.AddWithValue("@NIF", nif);
                    command2.ExecuteNonQuery();

                    transaction.Commit();
                    return true;
                }
                catch (SqlException ex)
                {

                    if (ex.Number == 50000)
                    {
                        MessageBox.Show(ex.Message);
                        return false;
                    }
                    else
                    {
                        throw;
                        return false;
                    }

                    transaction.Rollback();
                    return false;
                }
            }
        }


        public bool DeleteUser(string nif)
        {
            using (SqlConnection connection = getSGBDConnection())
            {
                connection.Open();
                SqlTransaction transaction = connection.BeginTransaction();

                try
                {
                    SqlCommand command = new SqlCommand("ONLINEGAMES_PLATFORM.eliminarutilizador", connection);
                    command.CommandType = CommandType.StoredProcedure;
                    command.Parameters.AddWithValue("@NIF", nif); 
                    command.Transaction = transaction; 
                    command.ExecuteNonQuery();

                    transaction.Commit();

                    return true;
                }
                catch (SqlException e)
                {
                    System.Diagnostics.Debug.WriteLine("SQL Error Number: " + e.Number); 
                    System.Diagnostics.Debug.WriteLine("SQL Error Message: " + e.Message);
                    return false;
                }

                catch (Exception e)
                {
                    // Se houve algum erro, faz rollback da transação
                    transaction.Rollback();

                    System.Diagnostics.Debug.WriteLine(e.Message);
                    System.Diagnostics.Debug.WriteLine(e.StackTrace);

                    return false;
                }

            }
        }


        public DataTable GetUserDetails(string nif)
        {
            using (SqlConnection connection = getSGBDConnection())
            {
                string query = "SELECT * FROM ONLINEGAMES_PLATFORM.UTILIZADOR WHERE NIF = @NIF";
                SqlCommand command = new SqlCommand(query, connection);
                command.Parameters.AddWithValue("@NIF", nif);
                connection.Open();
                SqlDataReader reader = command.ExecuteReader();

                DataTable dt = new DataTable();
                dt.Load(reader);
                return dt;
            }
        }

        public string GetUserEmail(string nif)
        {
            using (SqlConnection connection = getSGBDConnection())
            {
                string query = "SELECT Email FROM ONLINEGAMES_PLATFORM.UTILIZADOR WHERE NIF = @NIF";
                SqlCommand command = new SqlCommand(query, connection);
                command.Parameters.AddWithValue("@NIF", nif);
                connection.Open();
                return command.ExecuteScalar().ToString(); 
            }
        }


        private SqlConnection getSGBDConnection()
        {
            try
            {
                SqlConnection cn = new SqlConnection(DB_CONNECTIONSTRING);

                //MessageBox.Show("conectado com o banco de dado com sucesso! ");
                return cn;
            }
            catch (Exception ex)
            {
                MessageBox.Show("Erro ao conectar com o banco de dados: " + ex.Message);
                return null;
            }
        }


        private bool verifySGBDConnection()
        {
            if (cn == null)
                cn = getSGBDConnection();

            if (cn.State != ConnectionState.Open)
                cn.Open();

            return cn.State == ConnectionState.Open;
        }

        private List<string> ObterGenerosPreferidos(string nifComprador)
        {
            List<string> generosPreferidos = new List<string>();
            string queryString = "SELECT Nome_Genero FROM ONLINEGAMES_PLATFORM.GENERO_PREFERIDO WHERE NIF_Comprador = @NIF_Comprador";

            using (SqlCommand command = new SqlCommand(queryString, cn))
            {
                command.Parameters.AddWithValue("@NIF_Comprador", nifComprador);
                SqlDataReader reader = command.ExecuteReader();

                while (reader.Read())
                {
                    generosPreferidos.Add(reader.GetString(0));
                }
            }
            return generosPreferidos;
        }

        //butao login
        private void button1_Click(object sender, EventArgs e)
        {
            cn = getSGBDConnection();
            cn.Open();
            if (textBox2.Text != string.Empty || textBox1.Text != string.Empty)
            {
                SqlCommand cmd = new SqlCommand("SELECT * FROM ONLINEGAMES_PLATFORM.UTILIZADOR where Email='" + textBox1.Text + "' and Palavra_passe='" + textBox2.Text + "'", cn);
                SqlDataReader dr = cmd.ExecuteReader();
                if (dr.Read())
                {
                    username = textBox1.Text;
                    NIF_Comprador = dr["NIF"].ToString();  // Armazenar o NIF do comprador
                    dr.Close();
                    // Carrega o email do usuário no TextBox
                    emailbox.Text = GetUserEmail(NIF_Comprador);
                    MessageBox.Show("login successfully ");
                    LoginPanel.Hide();
                    GamePanel.Show();
                    CarregarDadosJogos();
                    List<string> generosPreferidos = ObterGenerosPreferidos(NIF_Comprador);

                    Action.Checked = generosPreferidos.Contains("Action");
                    Horror.Checked = generosPreferidos.Contains("Terror");
                    Fantasy.Checked = generosPreferidos.Contains("Fantasy");
                    Drama.Checked = generosPreferidos.Contains("Anime");
                    Romance.Checked = generosPreferidos.Contains("Romance");
                    Sports.Checked = generosPreferidos.Contains("Sports");
                    cn.Close();
                }
                else
                {
                    dr.Close();
                    MessageBox.Show("There's no account with that username and password", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }
            else
            {
                MessageBox.Show("Please insert all data.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        //email
        private void textBox1_TextChanged(object sender, EventArgs e)
        {

        }

        //password
        private void textBox2_TextChanged(object sender, EventArgs e)
        {
         
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void label4_Click(object sender, EventArgs e)
        {

        }
        
        //butao register
        private void button2_Click(object sender, EventArgs e)
        {
            panelRegister.Show();
            LoginPanel.Hide();
        }

        private void LoginPanel_Paint(object sender, PaintEventArgs e)
        {

        }

        private void checkBox1_CheckedChanged(object sender, EventArgs e)
        {
            if (!seePassword)
            {
                textBox2.PasswordChar = '\0';
            }
            else
            {
                textBox2.PasswordChar = '*';
            }
            seePassword = !seePassword;
        }

        //email 
        private void textBox3_TextChanged(object sender, EventArgs e)
        {

        }

        private void panelRegister_Paint(object sender, PaintEventArgs e)
        {
            label5.Show();
            emailRegister.Show();
            label6.Show();
            passRegister.Show();
            label7.Show();
            NifRegister.Show();
            label8.Show();
            label9.Show();
            Register.Show ();
            MoradaRegister.Show();
            label11.Show();
            emailbox.Show();
            changePass.Show();


        }

        private void Register_Click(object sender, EventArgs e)
        {
            string email = emailRegister.Text;
            string nif = NifRegister.Text;
            string password = passRegister.Text;
            string morada = MoradaRegister.Text;

            if (string.IsNullOrWhiteSpace(email) || string.IsNullOrWhiteSpace(password) || string.IsNullOrWhiteSpace(nif))
            {
                MessageBox.Show("Please insert every detail.");
            }
            else if (!email.Contains("@"))
            {
                MessageBox.Show("Invalid email");
            }
            else if (UserExists(email, password, nif))
            {
                MessageBox.Show("Email already in use!");
            }
            else
            {
                //  inserir o novo utilizador na tabela
                bool userInserted = InsertUser(email, password, nif, morada);
                if (userInserted)
                {
                    MessageBox.Show("User registered with success");
                    LoginPanel.Show();
                    panelRegister.Hide();
                }
              
            }
        }

        private void NifRegister_TextChanged(object sender, EventArgs e)
        {

        }

        private void passRegister_TextChanged(object sender, EventArgs e)
        {

        }

        private void label5_Click(object sender, EventArgs e)
        {

        }

        private void label6_Click(object sender, EventArgs e)
        {
           
        }


        //game panel
        private void panel1_Paint(object sender, PaintEventArgs e)
        {

        }

        private void ValorAvaliacao_TextChanged(object sender, EventArgs e)
        {

        }

        private void avaliar_Click(object sender, EventArgs e)
        {

        }

   

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

        private void ps4ToolStripMenuItem_Click(object sender, EventArgs e)
        {

        }

        private void Profile_Click(object sender, EventArgs e)
        {
            GamePanel.Hide();
            panelRegister.Hide();
            LoginPanel.Hide();
            PerfilPanel.Show();
           

        }

        private void PerfilPanel_Paint(object sender, PaintEventArgs e)
        {
            
            label10.Show();
        }

        private void GenPreferido_Click(object sender, EventArgs e)
        {
            mostrarGeneroFavorito = !mostrarGeneroFavorito;

            using (SqlConnection connection = getSGBDConnection())
            {
                connection.Open();
                if (mostrarGeneroFavorito)
                {
                    using (SqlCommand command = new SqlCommand("ONLINEGAMES_PLATFORM.GetJogosGeneroFavorito", connection))
                    {
                        command.CommandType = CommandType.StoredProcedure;

                       
                        command.Parameters.AddWithValue("@NIF_Comprador", NIF_Comprador);

                        using (SqlDataReader reader = command.ExecuteReader())
                        {
                            DataTable dataTable = new DataTable();
                            dataTable.Load(reader);

                           
                            dataGridView1.DataSource = dataTable;
                        }
                    }
                }
                else
                {
                    CarregarDadosJogos();

                }

            }
        }

        private void OrderTopSells_CheckedChanged(object sender, EventArgs e)
        {
            if (OrderTopSells.Checked)
            {
                orderBySeller.Checked = false;

                string sqlCommand = ("ONLINEGAMES_PLATFORM.GetJogosOrdenadosPorVendas");

                cn.Open();
                using (SqlCommand cmd = new SqlCommand(sqlCommand, cn))
                {
                    SqlDataAdapter da = new SqlDataAdapter(cmd);
                    DataTable dt = new DataTable();
                    da.Fill(dt);

                    dataGridView1.DataSource = dt;
                   // dataGridView1.Columns[dataGridView1.Columns.Count - 1].AutoSizeMode = DataGridViewAutoSizeColumnMode.Fill;

                }
                cn.Close();
            }
            else if (!orderBySeller.Checked)  // Se ambos estiverem desmarcados, chamar CarregarDadosJogos()
            {
                CarregarDadosJogos();

            }
        }


        private void orderBySeller_CheckedChanged(object sender, EventArgs e)
        {
            if (orderBySeller.Checked)
            {
                OrderTopSells.Checked = false;

                cn.Open();
                using (SqlCommand cmd = new SqlCommand("ONLINEGAMES_PLATFORM.GetJogosOrdenadosPorVendedor", cn))
                {
                    cmd.CommandType = CommandType.StoredProcedure;
                    SqlDataAdapter da = new SqlDataAdapter(cmd);
                    DataTable dt = new DataTable();
                    da.Fill(dt);

                    dataGridView1.DataSource = dt;
                    //dataGridView1.Columns[dataGridView1.Columns.Count - 1].AutoSizeMode = DataGridViewAutoSizeColumnMode.Fill;

                }
                cn.Close();
            }
            else if (!OrderTopSells.Checked)  // Se ambos estiverem desmarcados, chamar CarregarDadosJogos()
            {
                CarregarDadosJogos();   
            }
        }


        private void Comprar_Click(object sender, EventArgs e)
        {
            AvaliarVendedor.Hide();
            ValueVendedor.Hide();
            AddAvaliacao.Hide();

           
                if (dataGridView1.SelectedRows.Count == 1)
                {
                
                string nomeJogo = dataGridView1.SelectedRows[0].Cells["Name"].Value.ToString();
                //string id = dataGridView1.SelectedRows[0].Cells["ID"].Value.ToString();
                int idAnuncio = Convert.ToInt32(dataGridView1.SelectedRows[0].Cells["ID"].Value.ToString());
                nifVendedor = dataGridView1.SelectedRows[0].Cells["NIF Seller"].Value.ToString();
               // MessageBox.Show(id);
                string sql = "ONLINEGAMES_PLATFORM.ComprarJogo";
                SqlCommand cmd = new SqlCommand(sql, cn);
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("@NOME_JOGO", nomeJogo);
                cmd.Parameters.AddWithValue("@ID_ANUNCIO", idAnuncio);
                cmd.Parameters.AddWithValue("@NIF_COMPRADOR", NIF_Comprador);
             

                SqlParameter returnParameter = cmd.Parameters.Add("@ReturnVal", SqlDbType.Int);
                returnParameter.Direction = ParameterDirection.ReturnValue;

                cn.Open();
                try
                {
                    cmd.ExecuteNonQuery();
                    int returnValue = (int)cmd.Parameters["@ReturnVal"].Value;

                    if (returnValue > 0 )
                    {
                       

                        GenPreferido.Hide();
                        textBox3.Hide();
                        button3.Hide();
                        Comprar.Hide();
                        dataGridView1.Hide();
                        orderBySeller.Hide();
                        OrderTopSells.Hide();
                        Description.Hide();
                        gamesbought.Hide();
                        evaluationHistory.Hide();


                        Profile.Show();
                        AvaliarVendedor.Show();
                        ValueVendedor.Show();
                        AddAvaliacao.Show();

               
                        CarregarDadosJogos();
                        

                        cn.Close();
                    }
                    else
                    {
                        // Se a compra falhou (ou seja, se nenhuma linha foi afetada), mostrar uma mensagem de erro
                        MessageBox.Show("Sem sotck do jogo", "Erro", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    }
                }
                catch (SqlException ex)
                {
                    // Lidar com o erro.
                    MessageBox.Show(ex.Message, "Erro", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
                
            }
            else
            {
                MessageBox.Show("Por favor selecione uma linha para comprar.", "Erro", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
            cn.Close();
        }



        private void ValueVendedor_ValueChanged(object sender, EventArgs e)
        {

        }

        private void AddAvaliacao_Click(object sender, EventArgs e)
        {
            int rating = (int)ValueVendedor.Value;
            string NIF_Vendedor = this.nifVendedor; 
            DateTime data_avaliacao = DateTime.Now;

           
            string sqlRating = "ONLINEGAMES_PLATFORM.InserirAvaliacao";
            SqlCommand cmdRating = new SqlCommand(sqlRating, cn);
            cmdRating.CommandType = CommandType.StoredProcedure;
            cmdRating.Parameters.AddWithValue("@NIF_COMPRADOR", NIF_Comprador);
            cmdRating.Parameters.AddWithValue("@NIF_VENDEDOR", NIF_Vendedor);
            cmdRating.Parameters.AddWithValue("@AVALIACAO", rating);
            cmdRating.Parameters.AddWithValue("@data_avaliacao", data_avaliacao);

            cn.Open();
            try
            {
                cmdRating.ExecuteNonQuery();
            }
            catch (SqlException ex)
            {
                // Lidar com o erro.
                MessageBox.Show(ex.Message, "Erro", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
            cn.Close();

 
            AvaliarVendedor.Hide();
            ValueVendedor.Hide();
            AddAvaliacao.Hide();

            gamesbought.Show();
            evaluationHistory.Show();
            GenPreferido.Show();
            textBox3.Show();
            button3.Show();
            CarregarDadosJogos();
            dataGridView1.Show();
            Comprar.Show();
            orderBySeller.Show();
            OrderTopSells.Show();
            Description.Show();

        }
        private void textBox3_TextChanged_1(object sender, EventArgs e)
        {
            string searchText = textBox3.Text;
            cn.Open();

            using (SqlCommand cmd = new SqlCommand("ONLINEGAMES_PLATFORM.sp_SearchJogo", cn))
            {
                cmd.CommandType = CommandType.StoredProcedure;

                cmd.Parameters.AddWithValue("@searchText", searchText);

                SqlDataAdapter da = new SqlDataAdapter(cmd);
                DataTable dt = new DataTable();
                da.Fill(dt);

                dataGridView1.DataSource = dt;
                dataGridView1.Columns[dataGridView1.Columns.Count - 1].AutoSizeMode = DataGridViewAutoSizeColumnMode.Fill;

            }

            cn.Close();
        }



        private void procurar_Click(object sender, EventArgs e)
        {

        }

        private void AvalieVendedor_Click(object sender, EventArgs e)
        {

        }

        private void PerfilPanel_Paint_1(object sender, PaintEventArgs e)
        {
            label10.Show();
        }



        private void plataformaToolStripMenuItem_Click_1(object sender, EventArgs e)
        {
            CarregarDadosJogos();
        }


        private void ps4ToolStripMenuItem_Click_1(object sender, EventArgs e)
        {
            CarregarDadosJogosPorPlataforma("Ps4");
        }

        private void ps3ToolStripMenuItem_Click_1(object sender, EventArgs e)
        {
            CarregarDadosJogosPorPlataforma("ps3");
        }

        private void ps2ToolStripMenuItem_Click_1(object sender, EventArgs e)
        {
            CarregarDadosJogosPorPlataforma("ps2");
        }

        private void pcToolStripMenuItem_Click_1(object sender, EventArgs e)
        {
            CarregarDadosJogosPorPlataforma("pc");
        }

        private void switchToolStripMenuItem_Click_1(object sender, EventArgs e)
        {
            CarregarDadosJogosPorPlataforma("Nintendo Wii");
        }

        private void nintendoWiiToolStripMenuItem_Click(object sender, EventArgs e)
        {
            CarregarDadosJogosPorPlataforma("Nintendo Wii");
        }


        private void Action_CheckedChanged(object sender, EventArgs e)
        {

        }

        private void Horror_CheckedChanged(object sender, EventArgs e)
        {

        }

        private void Fantasy_CheckedChanged(object sender, EventArgs e)
        {

        }

        private void Drama_CheckedChanged(object sender, EventArgs e)
        {

        }

        private void Romance_CheckedChanged(object sender, EventArgs e)
        {

        }
        private void Sports_CheckedChanged(object sender, EventArgs e)
        {

        }

        private void AddGenre_Click(object sender, EventArgs e)
        {
            
            string nifComprador = NIF_Comprador;

            List<string> generosSelecionados = new List<string>();

   
            if (Action.Checked)
                generosSelecionados.Add("Action");
            if (Horror.Checked)
                generosSelecionados.Add("Terror");
            if (Fantasy.Checked)
                generosSelecionados.Add("Fantasy");
            if (Drama.Checked)
                generosSelecionados.Add("Anime");
            if (Romance.Checked)
                generosSelecionados.Add("Romance");
            if (Sports.Checked)
                generosSelecionados.Add("Sports");

            foreach (string genero in generosSelecionados)
            {
               
                    cn.Open();

                    string queryString = "INSERT INTO ONLINEGAMES_PLATFORM.GENERO_PREFERIDO (NIF_Comprador, Nome_Genero) VALUES (@NIF_Comprador, @Nome_Genero)";

                    using (SqlCommand command = new SqlCommand(queryString, cn))
                    {
                        command.Parameters.AddWithValue("@NIF_Comprador", nifComprador);
                        command.Parameters.AddWithValue("@Nome_Genero", genero);

                        command.ExecuteNonQuery();
                    }
                    cn.Close();
              
            }
            MessageBox.Show("Genre added successfully ");
        }

        private void label10_Click(object sender, EventArgs e)
        {

        }
        private void Description_Click(object sender, EventArgs e)
        {
            string nomeJogo = dataGridView1.SelectedRows[0].Cells["Name"].Value.ToString();
            string description = "";

            if (string.IsNullOrEmpty(nomeJogo))
            {
                MessageBox.Show("Game Name not found", "Erro");
                return;
            }

            

            using (SqlCommand cmd = new SqlCommand("SELECT DESCRICAO_JOGO FROM ONLINEGAMES_PLATFORM.JOGO WHERE NOME = @gameName", cn))
            {
                cn.Open();
                cmd.Parameters.AddWithValue("@gameName", nomeJogo);

                using (SqlDataReader reader = cmd.ExecuteReader())
                {
                    if (reader.Read())
                    {
                        description = reader.GetString(0);
                    }
                }
                cn.Close();
            }

            if (!string.IsNullOrEmpty(description))
            {
                MessageBox.Show(description, "Description of game");
            }
            else
            {
                MessageBox.Show("Description of game not found", "Erro");
            }
        }

        private void Logout_Click(object sender, EventArgs e)
        {
            NIF_Comprador = "";
            MessageBox.Show("Logging Out");
            PerfilPanel.Hide();
            LoginPanel.Show();
        }

        private void Eliminate_Click(object sender, EventArgs e)
        {
            //MessageBox.Show(NIF_Comprador);

            bool success = DeleteUser(NIF_Comprador);

            if (success)
            {
                // eliminado com sucesso
                MessageBox.Show("Sorry to see you leaving!\nYour account was eliminated with success.", "Account Eliminated", MessageBoxButtons.OK, MessageBoxIcon.Information);
                NIF_Comprador = "";
                PerfilPanel.Hide();
                LoginPanel.Show();
            }
            else
            {
                // Falha 
                MessageBox.Show("An error occurred while trying to eliminate your account.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }


        private void Back2_Click(object sender, EventArgs e)
        {
            GamePanel.Show();
        }

        private void emailbox_TextChanged(object sender, EventArgs e)
        {

        }

        private void changePass_Click(object sender, EventArgs e)
        {
            string oldPassword = OldPassword.Text;
            string newPassword = NewPassword.Text;

            if (string.IsNullOrWhiteSpace(oldPassword) || string.IsNullOrWhiteSpace(newPassword))
            {
                MessageBox.Show("Please insert both inputs of password", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            if (VerifyPassword(NIF_Comprador, oldPassword))
            {
                // Se a senha antiga está correta, procedemos com a mudança de senha
                if (ChangePassword(NIF_Comprador, newPassword))
                {
                    MessageBox.Show("Password changed successfully!");
                }
                else
                {
                    MessageBox.Show("Error while trying to change password.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }
            else
            {
                MessageBox.Show("Your current password doesnt match the input.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private bool VerifyPassword(string nif, string password)
        {
            using (SqlConnection connection = getSGBDConnection())
            {
                string query = "SELECT * FROM ONLINEGAMES_PLATFORM.UTILIZADOR WHERE NIF = @NIF AND Palavra_passe = @Password";
                SqlCommand command = new SqlCommand(query, connection);
                command.Parameters.AddWithValue("@NIF", nif);
                command.Parameters.AddWithValue("@Password", password);
                connection.Open();
                SqlDataReader reader = command.ExecuteReader();
                return reader.HasRows;
            }
        }

        private bool ChangePassword(string nif, string newPassword)
        {
            using (SqlConnection connection = getSGBDConnection())
            {
                string query = "UPDATE ONLINEGAMES_PLATFORM.UTILIZADOR SET Palavra_passe = @Password WHERE NIF = @NIF";
                SqlCommand command = new SqlCommand(query, connection);
                command.Parameters.AddWithValue("@NIF", nif);
                command.Parameters.AddWithValue("@Password", newPassword);
                connection.Open();
                int rowsAffected = command.ExecuteNonQuery();
                return rowsAffected == 1;
            }
        }

        private void label11_Click(object sender, EventArgs e)
        {

        }

        private void currentpass_Click(object sender, EventArgs e)
        {

        }

        private void OldPassword_TextChanged(object sender, EventArgs e)
        {

        }

        private void newpass_Click(object sender, EventArgs e)
        {

        }

        private void NewPassword_TextChanged(object sender, EventArgs e)
        {

        }

        private void gamesbought_Click(object sender, EventArgs e)
        {
            if (gamesBoughtClicked)
            {
                CarregarDadosJogos();
            }
            else
            {

                string currentNIF = NIF_Comprador; // Obter o NIF do usuário  

                using (SqlCommand cmd = new SqlCommand("ONLINEGAMES_PLATFORM.GetJogosComprados", cn))
                {
                    cmd.CommandType = CommandType.StoredProcedure;
                    cmd.Parameters.AddWithValue("@NIF_Comprador", currentNIF);

                    SqlDataAdapter da = new SqlDataAdapter(cmd);
                    DataTable dt = new DataTable();
                    da.Fill(dt);

                    dataGridView1.DataSource = dt;
                    dataGridView1.Columns[dataGridView1.Columns.Count-1].AutoSizeMode = DataGridViewAutoSizeColumnMode.Fill;

                }
                cn.Close();
            }

            gamesBoughtClicked = !gamesBoughtClicked;
        }



        private void evaluationHistory_Click(object sender, EventArgs e)
        {
            if (evaluationHistoryClicked)
            {
                CarregarDadosJogos();
            }
            else
            {
                string currentNIF = NIF_Comprador;

                cn.Open();

                using (SqlCommand cmd = new SqlCommand("ONLINEGAMES_PLATFORM.GetAvaliacoesVendedor", cn))
                {
                    cmd.CommandType = CommandType.StoredProcedure;
                    cmd.Parameters.AddWithValue("@NIF_Comprador", currentNIF);

                    SqlDataAdapter da = new SqlDataAdapter(cmd);
                    DataTable dt = new DataTable();
                    da.Fill(dt);

                    dataGridView1.DataSource = dt;
                    dataGridView1.Columns[dataGridView1.Columns.Count - 1].AutoSizeMode = DataGridViewAutoSizeColumnMode.Fill;
                }

                cn.Close();
            }
            evaluationHistoryClicked = !evaluationHistoryClicked;
        }

        private void removegenre_Click(object sender, EventArgs e)
        {
            string nifComprador = NIF_Comprador;

            List<string> generosParaRemover = new List<string>();

            if (Action.Checked)
                generosParaRemover.Add("Action");
            if (Horror.Checked)
                generosParaRemover.Add("Terror");
            if (Fantasy.Checked)
                generosParaRemover.Add("Fantasy");
            if (Drama.Checked)
                generosParaRemover.Add("Anime");
            if (Romance.Checked)
                generosParaRemover.Add("Romance");
            if (Sports.Checked)
                generosParaRemover.Add("Sports");

            foreach (string genero in generosParaRemover)
            {
                cn.Open();

                string queryString = "DELETE FROM ONLINEGAMES_PLATFORM.GENERO_PREFERIDO WHERE NIF_Comprador = @NIF_Comprador AND Nome_Genero = @Nome_Genero";

                using (SqlCommand command = new SqlCommand(queryString, cn))
                {
                    command.Parameters.AddWithValue("@NIF_Comprador", nifComprador);
                    command.Parameters.AddWithValue("@Nome_Genero", genero);

                    command.ExecuteNonQuery();
                }
                cn.Close();

                switch (genero)
                {
                    case "Action":
                        Action.Checked = false;
                        break;
                    case "Terror":
                        Horror.Checked = false;
                        break;
                    case "Fantasy":
                        Fantasy.Checked = false;
                        break;
                    case "Anime":
                        Drama.Checked = false;
                        break;
                    case "Romance":
                        Romance.Checked = false;
                        break;
                    case "Sports":
                        Sports.Checked = false;
                        break;
                }
            }
            MessageBox.Show("Genre removed successfully ");
        }

        private void favorites_Click(object sender, EventArgs e)
        {
            string storedProcedureName = "ONLINEGAMES_PLATFORM.sp_AddToFavorites";

            string nomeJogo = dataGridView1.SelectedRows[0].Cells["Name"].Value.ToString();
            int idAnuncio = Convert.ToInt32(dataGridView1.SelectedRows[0].Cells["ID"].Value.ToString());
            nifVendedor = dataGridView1.SelectedRows[0].Cells["NIF Seller"].Value.ToString();

            cn.Open();

            using (SqlCommand command = new SqlCommand(storedProcedureName, cn))
            {
                command.CommandType = CommandType.StoredProcedure;

                command.Parameters.Add(new SqlParameter("@NIF_COMPRADOR", NIF_Comprador));
                command.Parameters.Add(new SqlParameter("@NOME_JOGO", nomeJogo));
                command.Parameters.Add(new SqlParameter("@ID_ANUNCIO", idAnuncio));

                command.ExecuteNonQuery();
            }
            cn.Close();

            MessageBox.Show(nomeJogo + " added to your list of favorites.", "Information", MessageBoxButtons.OK, MessageBoxIcon.Information);
        }


       

        private void fav_Click(object sender, EventArgs e)
        {
            if (showingFavorites)
            {
                CarregarDadosJogos();
                showingFavorites = false;
            }
            else
            {
                string storedProcedureName = "ONLINEGAMES_PLATFORM.sp_GetFavoriteGames";

                cn.Open();

                using (SqlCommand command = new SqlCommand(storedProcedureName, cn))
                {
                    command.CommandType = CommandType.StoredProcedure;

                    command.Parameters.Add(new SqlParameter("@NIF_COMPRADOR", NIF_Comprador));

                    SqlDataAdapter da = new SqlDataAdapter(command);
                    DataTable dt = new DataTable();
                    da.Fill(dt);
                    dataGridView1.DataSource = dt;
                    dataGridView1.Columns[dataGridView1.Columns.Count - 1].AutoSizeMode = DataGridViewAutoSizeColumnMode.Fill;

                }

                cn.Close();

                showingFavorites = true;
            }
        }


    }
}

