using Restaurante.Models;
using static Azure.Core.HttpHeader;

namespace Restaurante.Service
{
    public class PedidoRepositorio :IPedidocs
    {
        private Conexion conex = new Conexion();

        public void add(TaPedido taPedido)
        {
            conex.TaPedidos.Add(taPedido);
            conex.SaveChanges();    
        }

        public void eleminar(int id)
        {
            conex.Remove(ObtenerPorid(id));
            conex.SaveChanges();
        }

        public IEnumerable<TaPedido> GetPedidos()
        {
            return conex.TaPedidos;
        }

        public void modificar(TaPedido taPedido)
        {
            var obj = conex.TaPedidos.Where(tpro => tpro.IdPedido == taPedido.IdPedido)
            .FirstOrDefault();

            obj.Total = taPedido.Total;
            obj.Dia = taPedido.Dia;
            obj.IdPersonal = taPedido.IdPersonal;
            obj.HoraFinPedido = taPedido.HoraFinPedido;
            obj.TaPedidoDetalles = taPedido.TaPedidoDetalles;

            conex.SaveChanges();
        }

        public TaPedido ObtenerPorid(int id)
        {
            return conex.TaPedidos.Where(tpro => tpro.IdPedido == id).FirstOrDefault();
        }
    }
}
